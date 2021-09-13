package com.blankmemo.courier

/**
 *
 * A mapper interface for mapping [ApiResponse.Failure.Error] response as a custom [V] instance model.
 *
 * @see [ApiErrorModelMapper]
 */
fun interface ApiErrorModelMapper<V> {

    /**
     * maps the [ApiResponse.Failure.Error] to the [V] using the mapper.
     *
     * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
     * @return A custom [V] error response model.
     */
    fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): V
}
