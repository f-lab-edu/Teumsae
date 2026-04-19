package com.hyc.teumsae.core.data.datasource.local

import com.hyc.teumsae.core.datastore.UserInfoDatastore
import com.hyc.teumsae.core.domain.model.Station
import javax.inject.Inject

class SettingLocalDataSource @Inject constructor(
    private val userInfoDatastore: UserInfoDatastore
) {
    suspend fun setFirstSetup(
        startStation: Station,
        endStation: Station,
        level: Int,
        categories: Set<String>
    ) {
        userInfoDatastore.setStartStation(startStation.name)
        userInfoDatastore.setEndStation(endStation.name)
        userInfoDatastore.setLevel(level)
        userInfoDatastore.setCategories(categories)
    }
}