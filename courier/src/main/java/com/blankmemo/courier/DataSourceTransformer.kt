@file:JvmName("DataSourceTransformer")
@file:JvmMultifileClass

package com.blankmemo.courier

/**
 * Changes an instance of the [DataSource] interface to the [ResponseDataSource].
 */
fun <T> DataSource<T>.toResponseDataSource(): ResponseDataSource<T> {
  requireNotNull(this is ResponseDataSource)
  return this as ResponseDataSource<T>
}
