package com.example.endlessscrollsampleproject.di

import com.example.endlessscrollsampleproject.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideBaseUrl(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun provideRetrofitClient(baseUrl: String, gsonConverter: GsonConverterFactory): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverter).build()

    @Singleton
    @Provides
    fun provideApiService(retrofitClient: Retrofit): ApiService =
        retrofitClient.create(ApiService::class.java)
}