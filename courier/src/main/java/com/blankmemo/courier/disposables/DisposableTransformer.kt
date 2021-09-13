@file:Suppress("unused")
@file:JvmName("DisposableTransformer")
@file:JvmMultifileClass

package com.blankmemo.courier.disposables

import com.blankmemo.courier.request
import retrofit2.Call

/**
 *
 * Returns an instance of [Disposable] from a [Call].
 */
fun <T> Call<T>.disposable(): Disposable {
    val call = this
    return object : Disposable {
        override fun dispose() {
            if (call.isExecuted && !isDisposed()) {
                call.cancel()
            }
        }

        override fun isDisposed() = call.isCanceled
    }
}

/**
 * Joins onto [CompositeDisposable] as a disposable. must be called before [request].
 */
fun <T> Call<T>.joinDisposable(compositeDisposable: CompositeDisposable): Call<T> = apply {
    compositeDisposable.add(disposable())
}
