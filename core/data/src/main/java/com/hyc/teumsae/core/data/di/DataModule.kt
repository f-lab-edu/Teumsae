package com.hyc.teumsae.core.data.di

import com.hyc.teumsae.core.data.repository.SettingRepositoryImpl
import com.hyc.teumsae.core.domain.repository.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindSettingRepository(impl: SettingRepositoryImpl): SettingRepository
}