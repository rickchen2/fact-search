package com.blankmemo.courier

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * DataSourceCallAdapter is an call adapter for creating [DataSource] from service method.
 *
 * request API network call asynchronously and returns [DataSource].
 */
class DataSourceCallAdapter<R> constructor(
    private val responseType: Type
) : CallAdapter<R, DataSource<R>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): DataSource<R> {
        val responseDataSource: ResponseDataSource<R> = ResponseDataSource()
        return responseDataSource.combine(call, null)
    }
}
