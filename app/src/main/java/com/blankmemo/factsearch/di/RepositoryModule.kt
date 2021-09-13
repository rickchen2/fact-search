package com.blankmemo.factsearch.di

import com.blankmemo.factsearch.network.FactSearchClient
import com.blankmemo.factsearch.persistence.PokemonDao
import com.blankmemo.factsearch.persistence.PokemonInfoDao
import com.blankmemo.factsearch.repository.DetailRepository
import com.blankmemo.factsearch.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    factSearchClient: FactSearchClient,
    pokemonDao: PokemonDao,
    coroutineDispatcher: CoroutineDispatcher
  ): MainRepository {
    return MainRepository(factSearchClient, pokemonDao, coroutineDispatcher)
  }

  @Provides
  @ViewModelScoped
  fun provideDetailRepository(
    factSearchClient: FactSearchClient,
    pokemonInfoDao: PokemonInfoDao,
    coroutineDispatcher: CoroutineDispatcher
  ): DetailRepository {
    return DetailRepository(factSearchClient, pokemonInfoDao, coroutineDispatcher)
  }
}
