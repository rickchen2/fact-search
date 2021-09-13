package com.blankmemo.factsearch.repository

import androidx.annotation.WorkerThread
import com.blankmemo.courier.map
import com.blankmemo.courier.onError
import com.blankmemo.courier.onException
import com.blankmemo.courier.suspendOnSuccess
import com.blankmemo.courier.ApiResponse
import com.blankmemo.factsearch.mapper.ErrorResponseMapper
import com.blankmemo.factsearch.model.FactSearchErrorResponse
import com.blankmemo.factsearch.model.Pokemon
import com.blankmemo.factsearch.network.FactSearchClient
import com.blankmemo.factsearch.persistence.PokemonDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val factSearchClient: FactSearchClient,
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var pokemons = pokemonDao.getPokemonList(page)
        if (pokemons.isEmpty()) {
            /**
             * fetches a list of [Pokemon] from the network and getting [ApiResponse] asynchronously.
             * @see [suspendOnSuccess]
             */
            val response = factSearchClient.fetchPokemonList(page = page)
            response.suspendOnSuccess {
                pokemons = data.results
                pokemons.forEach { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemonList(pokemons)
                emit(pokemonDao.getAllPokemonList(page))
            }
                // handles the case when the API request gets an error response.
                // e.g., internal server error.
                .onError {
                    /** maps the [ApiResponse.Failure.Error] to the [FactSearchErrorResponse] using the mapper. */
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                // handles the case when the API request gets an exception response.
                // e.g., network connection error.
                .onException { onError(message) }
        } else {
            emit(pokemonDao.getAllPokemonList(page))
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
