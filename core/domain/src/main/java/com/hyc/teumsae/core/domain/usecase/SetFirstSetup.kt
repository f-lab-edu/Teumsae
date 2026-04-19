package com.hyc.teumsae.core.domain.usecase

import com.hyc.teumsae.core.domain.model.Station
import com.hyc.teumsae.core.domain.repository.SettingRepository
import javax.inject.Inject

class SetFirstSetup @Inject constructor(
    private val settingRepository: SettingRepository
) {
    suspend operator fun invoke(
        startStation: Station,
        endStation: Station,
        level: Int,
        categories: Set<String>
    ): Result<Unit> = settingRepository.setFirstSetup(startStation, endStation, level, categories)
}
