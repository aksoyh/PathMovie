package com.aksoyhasan.path.di

import com.aksoyhasan.path.data.repository.MarvelRepository
import com.aksoyhasan.path.data.repositorySource.MarvelRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun marvelRepositorySource(dataRepository: MarvelRepository): MarvelRepositorySource
}
