package com.blankmemo.courier.coroutines

import com.blankmemo.courier.DataSource
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 *
 * CoroutinesDataSourceCallAdapter is an coroutines call adapter for creating [DataSource] from service method.
 *
 * request API network call asynchronously and returns [DataSource].
 */
class CoroutinesDataSourceCallAdapter constructor(
    private val responseType: Type
) : CallAdapter<Type, Call<DataSource<Type>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<Type>): Call<DataSource<Type>> {
        return DataSourceCallDelegate(call)
    }
}
