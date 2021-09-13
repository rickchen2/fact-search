package com.blankmemo.factsearch.network

import com.blankmemo.courier.ApiResponse
import com.blankmemo.factsearch.model.PokemonInfo
import com.blankmemo.factsearch.model.PokemonResponse
import javax.inject.Inject

class FactSearchClient @Inject constructor(
  private val factSearchService: FactSearchService
) {

  suspend fun fetchPokemonList(
    page: Int
  ): ApiResponse<PokemonResponse> =
    factSearchService.fetchPokemonList(
      limit = PAGING_SIZE,
      offset = page * PAGING_SIZE
    )

  suspend fun fetchPokemonInfo(
    name: String
  ): ApiResponse<PokemonInfo> =
    factSearchService.fetchPokemonInfo(
      name = name
    )

  companion object {
    private const val PAGING_SIZE = 20
  }
}
