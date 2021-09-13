package com.blankmemo.courier.disposables

/**
 *
 * A definition for canceling when works should be disposed.
 */
interface Disposable {

  /** dispose the resource. */
  fun dispose()

  /** returns true if this resource has been disposed. */
  fun isDisposed(): Boolean
}
