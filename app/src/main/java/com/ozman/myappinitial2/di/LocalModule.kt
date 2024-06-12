package com.ozman.myappinitial2.di

import com.ozman.myappinitial2.data.remote.ApiService
import com.ozman.myappinitial2.data.repository.RepositoryImp
import com.ozman.myappinitial2.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to provide local dependencies.
 * Provides the Repository implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    /**
     * Provides a singleton instance of Repository.
     * @param apiService Instance of ApiService used by the Repository.
     * @return Repository implementation.
     */
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImp(apiService)
    }
}