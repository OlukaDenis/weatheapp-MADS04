package com.mcdenny.domain.repository

interface UtilRepository {
    fun getNetworkError(throwable: Throwable): String
}