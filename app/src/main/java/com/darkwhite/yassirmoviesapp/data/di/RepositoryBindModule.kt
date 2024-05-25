package com.darkwhite.yassirmoviesapp.data.di

import com.darkwhite.yassirmoviesapp.data.implementation.DataRepositoryImpl
import com.darkwhite.yassirmoviesapp.data.repository.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {
  
  @Singleton
  @Binds
  abstract fun bindsDataRepository(impl: DataRepositoryImpl): DataRepository
}