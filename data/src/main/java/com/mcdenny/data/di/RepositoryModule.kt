package com.mcdenny.data.di

import com.mcdenny.data.impl.CacheRepositoryImpl
import com.mcdenny.data.impl.RemoteRepositoryImpl
import com.mcdenny.data.impl.UtilRepositoryImpl
import com.mcdenny.domain.repository.CacheRepository
import com.mcdenny.domain.repository.RemoteRepository
import com.mcdenny.domain.repository.UtilRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindRepository(impl: RemoteRepositoryImpl): RemoteRepository

    @Singleton
    @Binds
    fun bindLocalRepository(impl: CacheRepositoryImpl): CacheRepository

    @Singleton
    @Binds
    fun bindUtilRepository(impl: UtilRepositoryImpl): UtilRepository

}