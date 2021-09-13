package com.blankmemo.factsearch.mapper

import com.blankmemo.courier.ApiErrorModelMapper
import com.blankmemo.courier.ApiResponse
import com.blankmemo.courier.message
import com.blankmemo.factsearch.model.FactSearchErrorResponse


/**
 * A mapper for mapping [ApiResponse.Failure.Error] response as custom [FactSearchErrorResponse] instance.
 *
 * @see [ApiErrorModelMapper]
 */
object ErrorResponseMapper : ApiErrorModelMapper<FactSearchErrorResponse> {

  /**
   * maps the [ApiResponse.Failure.Error] to the [FactSearchErrorResponse] using the mapper.
   *
   * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
   * @return A customized [FactSearchErrorResponse] error response.
   */
  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): FactSearchErrorResponse {
    return FactSearchErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
