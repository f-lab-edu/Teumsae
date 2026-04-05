package com.hyc.teumsae.core.network.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    fun provideRetrofit() : Retrofit =
//        Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
}