package com.mcdenny.data.impl

import com.mcdenny.data.utils.resolveError
import com.mcdenny.domain.repository.UtilRepository
import javax.inject.Inject

class UtilRepositoryImpl @Inject constructor(): UtilRepository {
    override fun getNetworkError(throwable: Throwable): String = throwable.resolveError()
}