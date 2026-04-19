package com.hyc.teumsae.core.data.repository

import com.hyc.teumsae.core.data.datasource.local.SettingLocalDataSource
import com.hyc.teumsae.core.domain.model.Station
import com.hyc.teumsae.core.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val localDataSource: SettingLocalDataSource
) : SettingRepository {
    override suspend fun setFirstSetup(
        startStation: Station,
        endStation: Station,
        level: Int,
        categories: Set<String>
    ): Result<Unit> = runCatching {
        localDataSource.setFirstSetup(startStation, endStation, level, categories)
    }
}