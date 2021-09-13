package com.blankmemo.factsearch.model

/**
 * A customized pokemon error response.
 *
 * @param code A network response code.
 * @param message A network error message.
 */
data class FactSearchErrorResponse(
  val code: Int,
  val message: String?
)
