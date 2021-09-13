package com.blankmemo.courier

/**
 * A callback interface for observing [ApiResponse] value updating.
 */
fun interface ResponseObserver<T> {

    /** observes a new [ApiResponse] value. */
    fun observe(response: ApiResponse<T>)
}
