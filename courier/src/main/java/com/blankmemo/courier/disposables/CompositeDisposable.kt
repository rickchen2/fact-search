@file:Suppress("unused")

package com.blankmemo.courier.disposables

/**
 *
 * A disposable container that can hold onto multiple other disposables.
 */
class CompositeDisposable {

    @Volatile
    var disposed: Boolean = false
        private set

    private var disposables: MutableSet<Disposable>? = hashSetOf()

    /** adds a new [Disposable] to this [CompositeDisposable] if not yet disposed. */
    fun add(disposable: Disposable) {
        if (disposable.isDisposed()) {
            return
        }

        if (!disposed) {
            synchronized(this) {
                if (!disposed) {
                    disposables?.add(disposable)
                    return
                }
            }
        }
    }

    /** removes a [Disposable] from this [CompositeDisposable] and dispose the target. */
    fun remove(disposable: Disposable) {
        if (!disposed) {
            synchronized(this) {
                if (disposed || disposables?.remove(disposable) == false) {
                    return
                }
            }
            disposable.dispose()
        }
    }

    /** disposes all disposables that are currently part of this [CompositeDisposable]. */
    fun clear() {
        if (!disposed) {
            var mutableCollection: MutableCollection<Disposable>?
            synchronized(this) {
                if (disposed) {
                    return
                }
                mutableCollection = disposables
                disposables = null
                disposed = true
            }
            mutableCollection?.forEach(Disposable::dispose)
        }
    }
}
