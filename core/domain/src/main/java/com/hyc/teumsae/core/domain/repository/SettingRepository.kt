package com.hyc.teumsae.core.domain.repository

import com.hyc.teumsae.core.domain.model.Station

interface SettingRepository {
    suspend fun setFirstSetup(
        startStation: Station,
        endStation: Station,
        level: Int,
        categories: Set<String>
    ): Result<Unit>
}