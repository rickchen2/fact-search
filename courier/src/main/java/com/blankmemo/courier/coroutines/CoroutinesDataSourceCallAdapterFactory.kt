package com.blankmemo.courier.coroutines

import com.blankmemo.courier.DataSource
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *
 * CoroutinesDataSourceCallAdapterFactory is an coroutines call adapter factory for creating [DataSource].
 *
 * Adding this class to [Retrofit] allows you to return on [DataSource] from service method.
 *
 * ```
 * @GET("DisneyPosters.json")
 * suspend fun fetchDisneyPosterList(): DataSource<List<Poster>>
 * ```
 */
class CoroutinesDataSourceCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CoroutinesDataSourceCallAdapter? = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                DataSource::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    CoroutinesDataSourceCallAdapter(resultType)
                }
                else -> null
            }
        }
        else -> null
    }

    companion object {
        @JvmStatic
        fun create(): CoroutinesDataSourceCallAdapterFactory =
            CoroutinesDataSourceCallAdapterFactory()
    }
}
