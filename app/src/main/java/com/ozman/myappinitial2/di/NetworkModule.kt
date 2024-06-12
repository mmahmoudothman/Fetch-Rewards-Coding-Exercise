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

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun providesServices(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}