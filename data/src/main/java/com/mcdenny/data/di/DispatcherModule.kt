package com.mcdenny.data.di

import com.mcdenny.domain.dispacher.AppDispatcher
import com.mcdenny.domain.impl.AppDisptacherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    @Singleton
    internal abstract fun bindDispatcher(impl: AppDisptacherImpl): AppDispatcher
}