package ru.hse.glimpse.utils.coroutines

import kotlin.coroutines.cancellation.CancellationException

// todo: delete nahyi.
inline fun <T, R> T.runCatchingCancellable(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        if (e is CancellationException) {
            throw e
        }
        Result.failure(e)
    }
}
