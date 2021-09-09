package com.blankmemo.factsearch.data

import com.blankmemo.factsearch.data.remote.RemoteDataSource
import com.blankmemo.factsearch.model.BaseApiResponse
import com.blankmemo.factsearch.model.DogResponse
import com.blankmemo.factsearch.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getDog(): Flow<NetworkResult<DogResponse>> {
        return flow<NetworkResult<DogResponse>> {
            emit(safeApiCall { remoteDataSource.getDog() })
        }.flowOn(Dispatchers.IO)
    }

}
