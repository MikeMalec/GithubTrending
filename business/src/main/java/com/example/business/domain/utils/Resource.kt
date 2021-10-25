package com.example.business.domain.utils

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: String? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}