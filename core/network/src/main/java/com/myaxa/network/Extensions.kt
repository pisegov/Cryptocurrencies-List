package com.myaxa.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T, R> T.safeCall(crossinline block: suspend () -> R): Result<R> =
    try {
        withContext(Dispatchers.IO) {
            Result.success(block())
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
