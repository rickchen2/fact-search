package com.blankmemo.factsearch.data.remote

import com.blankmemo.factsearch.model.DogResponse
import com.blankmemo.factsearch.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface DogService {

    @GET(Constants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>
}
