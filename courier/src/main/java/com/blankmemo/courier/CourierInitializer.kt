package com.blankmemo.courier

import com.blankmemo.courier.operators.CourierOperator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import okio.Timeout
import kotlin.coroutines.CoroutineContext

/**
 *
 * CourierInitializer is a rules and strategies initializer of the network response.
 */
object CourierInitializer {

    /**
     *
     * determines the success code range of network responses.
     *
     * if a network request is successful and the response code is in the [successCodeRange],
     * its response will be a [ApiResponse.Success].
     *
     * if a network request is successful but out of the [successCodeRange] or failure,
     * the response will be a [ApiResponse.Failure.Error].
     * */
    @JvmStatic
    var successCodeRange: IntRange = 200..299

    /**

     * A global Operator that operates on [ApiResponse]s globally on each response.
     *
     * [com.blankmemo.courier.operators.ApiResponseOperator] which allows you to handle success and error response instead of
     * the [ApiResponse.onSuccess], [ApiResponse.onError], [ApiResponse.onException] transformers.
     * [com.blankmemo.courier.operators.ApiResponseSuspendOperator] can be used for suspension scope.
     *
     * Via setting a [courierOperator], we don't need to set operator for every [ApiResponse].
     */
    @JvmStatic
    var courierOperator: CourierOperator? = null

    /**
     *
     * A [CoroutineContext] for operating the [courierOperator] when it extends
     * the [com.blankmemo.courier.operators.ApiResponseSuspendOperator].
     */
    @JvmSynthetic
    @OptIn(DelicateCoroutinesApi::class)
    var courierOperatorContext: CoroutineContext =
        Dispatchers.IO + GlobalScope.coroutineContext

    /**
     *
     * A global [Timeout] for operating the [com.blankmemo.courier.coroutines.CoroutinesResponseCallAdapterFactory]
     * or [com.blankmemo.courier.coroutines.CoroutinesDataSourceCallAdapterFactory] when API requests.
     *
     * Returns a timeout that spans the entire call: resolving DNS, connecting, writing the request
     * body, server processing, and reading the response body. If the call requires redirects or
     * retries all must complete within one timeout period.
     */
    @JvmStatic
    var courierTimeout: Timeout? = null
}
