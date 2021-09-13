package com.blankmemo.courier

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * DataSourceCallAdapterFactory is an call adapter factory for creating [DataSource].
 *
 * Adding this class to [Retrofit] allows you to return on [DataSource] from service method.
 *
 * ```
 * @GET("DisneyPosters.json")
 * fun fetchDisneyPosterList(): DataSource<List<Poster>>
 * ```
 */
class DataSourceCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val enclosingType = returnType as ParameterizedType
        val actualType = enclosingType.actualTypeArguments[0]
        return DataSourceCallAdapter<Type>(actualType)
    }

    companion object {
        @JvmStatic
        fun create(): DataSourceCallAdapterFactory = DataSourceCallAdapterFactory()
    }
}
