package com.ozman.myappinitial2.di

import com.ozman.myappinitial2.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module to provide network dependencies.
 * Provides OkHttpClient, Retrofit, and ApiService.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton instance of OkHttpClient with logging interceptor.
     * @return Configured OkHttpClient instance.
     */
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    /**
     * Provides a singleton instance of Retrofit configured with the base URL and OkHttpClient.
     * @param okHttpClient OkHttpClient instance used by Retrofit.
     * @return Configured Retrofit instance.
     */
    @Provides
    @Singleton
    fun providesServices(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    /**
     * Provides a singleton instance of ApiService.
     * @param retrofit Retrofit instance used to create ApiService.
     * @return Configured ApiService instance.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}