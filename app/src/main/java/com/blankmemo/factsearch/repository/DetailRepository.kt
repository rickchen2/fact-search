package com.blankmemo.factsearch.repository

import androidx.annotation.WorkerThread
import com.blankmemo.courier.map
import com.blankmemo.courier.onError
import com.blankmemo.courier.onException
import com.blankmemo.courier.suspendOnSuccess
import com.blankmemo.factsearch.mapper.ErrorResponseMapper
import com.blankmemo.factsearch.model.PokemonInfo
import com.blankmemo.factsearch.network.FactSearchClient
import com.blankmemo.factsearch.persistence.PokemonInfoDao
import com.blankmemo.factsearch.model.FactSearchErrorResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val factSearchClient: FactSearchClient,
    private val pokemonInfoDao: PokemonInfoDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow<PokemonInfo?> {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if (pokemonInfo == null) {
            /**
             * fetches a [PokemonInfo] from the network and getting [ApiResponse] asynchronously.
             * @see [suspendOnSuccess]
             */
            val response = factSearchClient.fetchPokemonInfo(name = name)
            response.suspendOnSuccess {
                pokemonInfoDao.insertPokemonInfo(data)
                emit(data)
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
            emit(pokemonInfo)
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
