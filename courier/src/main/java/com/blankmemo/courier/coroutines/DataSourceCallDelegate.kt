package com.blankmemo.courier.coroutines

import com.blankmemo.courier.DataSource
import com.blankmemo.courier.ResponseDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * DataSourceCallDelegate is a delegate [Call] proxy for handling and transforming normal generic type [T]
 * as [DataSource] that wrapping [T] data from the network responses.
 */
internal class DataSourceCallDelegate<T>(proxy: Call<T>) : CallDelegate<T, DataSource<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<DataSource<T>>) {
        val responseDataSource = ResponseDataSource<T>().combine(proxy, null)
        callback.onResponse(this@DataSourceCallDelegate, Response.success(responseDataSource))
    }

    override fun cloneImpl() = DataSourceCallDelegate(proxy.clone())
}
