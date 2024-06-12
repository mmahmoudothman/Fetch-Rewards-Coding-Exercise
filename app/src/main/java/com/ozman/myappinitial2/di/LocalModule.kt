package com.ozman.myappinitial2.di

import com.ozman.myappinitial2.data.remote.ApiService
import com.ozman.myappinitial2.data.repository.RepositoryImp
import com.ozman.myappinitial2.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImp(apiService)
    }

}