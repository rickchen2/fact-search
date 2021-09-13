@file:Suppress("unused")

package com.blankmemo.courier.executors

import androidx.annotation.RestrictTo

/**
 * A static class that serves as a central point to execute common tasks.
 *
 * @hide This API is not final.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal class ArchTaskExecutor private constructor() : TaskExecutor() {

  private var mDelegate: TaskExecutor

  private val mDefaultTaskExecutor: TaskExecutor

  init {
    mDefaultTaskExecutor = DefaultTaskExecutor()
    mDelegate = mDefaultTaskExecutor
  }

  /**
   * Sets a delegate to handle task execution requests.
   *
   *
   * If you have a common executor, you can set it as the delegate and App Toolkit components will
   * use your executors. You may also want to use this for your tests.
   *
   *
   * Calling this method with `null` sets it to the default TaskExecutor.
   *
   * @param taskExecutor The task executor to handle task requests.
   */
  fun setDelegate(taskExecutor: TaskExecutor?) {
    mDelegate = taskExecutor ?: mDefaultTaskExecutor
  }

  override fun executeOnDiskIO(runnable: Runnable) {
    mDelegate.executeOnDiskIO(runnable)
  }

  override fun postToMainThread(runnable: Runnable, duration: Long) {
    mDelegate.postToMainThread(runnable, duration)
  }

  override val isMainThread: Boolean
    get() = mDelegate.isMainThread

  companion object {
    @Volatile
    private lateinit var sInstance: ArchTaskExecutor

    /**
     * Returns an instance of the task executor.
     *
     * @return The singleton ArchTaskExecutor.
     */
    val instance: ArchTaskExecutor
      get() {
        if (Companion::sInstance.isInitialized) {
          return sInstance
        }
        synchronized(ArchTaskExecutor::class.java) {
          if (!Companion::sInstance.isInitialized) {
            sInstance = ArchTaskExecutor()
          }
        }
        return sInstance
      }
  }
}
