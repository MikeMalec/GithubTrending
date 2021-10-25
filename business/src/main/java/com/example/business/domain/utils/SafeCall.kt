package com.example.business.domain.utils

suspend fun <T> safeCall(call: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(call())
    } catch (throwable: Throwable) {
        Resource.Error(throwable.message)
    }
}