package com.blankmemo.factsearch.di

import com.blankmemo.courier.coroutines.CoroutinesResponseCallAdapterFactory
import com.blankmemo.factsearch.network.HttpRequestInterceptor
import com.blankmemo.factsearch.network.FactSearchClient
import com.blankmemo.factsearch.network.FactSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokedexService(retrofit: Retrofit): FactSearchService {
        return retrofit.create(FactSearchService::class.java)
    }

    @Provides
    @Singleton
    fun providePokedexClient(factSearchService: FactSearchService): FactSearchClient {
        return FactSearchClient(factSearchService)
    }
}
