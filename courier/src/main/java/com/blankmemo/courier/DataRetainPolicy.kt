package com.blankmemo.courier

/**
 * [DataRetainPolicy] is a policy for retaining data on the internal storage
 * when the same request is called from [ResponseDataSource].
 */
enum class DataRetainPolicy {
    // Retain the fetched data on the memory storage temporarily.
    // If request again, returns the retained data instead of re-fetching from the network.
    RETAIN,

    // Re-fetch data from the network every time.
    NO_RETAIN
}
