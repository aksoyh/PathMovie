package com.aksoyhasan.path.di

import com.aksoyhasan.path.data.error.mapper.ErrorMapper
import com.aksoyhasan.path.data.error.mapper.ErrorMapperSource
import com.aksoyhasan.path.useCase.errors.ErrorManager
import com.aksoyhasan.path.useCase.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
