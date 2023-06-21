package com.mcdenny.domain.models

sealed class Resource<out R> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: String) : Resource<Nothing>()
}
