package com.hyc.teumsae.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoDatastore @Inject constructor(
    private val datastore: DataStore<Preferences>
) {
    val startStation: Flow<String?> =
        datastore.data.map { it[DatastoreKeys.UserInfo.START_STATION] }

    val endStation: Flow<String?> =
        datastore.data.map { it[DatastoreKeys.UserInfo.END_STATION] }

    val level: Flow<Int?> =
        datastore.data.map { it[DatastoreKeys.UserInfo.LEVEL] }

    val categories: Flow<Set<String>> =
        datastore.data.map { it[DatastoreKeys.UserInfo.CATEGORIES] ?: emptySet() }

    suspend fun setStartStation(value: String) {
        datastore.edit { it[DatastoreKeys.UserInfo.START_STATION] = value }
    }

    suspend fun setEndStation(value: String) {
        datastore.edit { it[DatastoreKeys.UserInfo.END_STATION] = value }
    }

    suspend fun setLevel(value: Int) {
        datastore.edit { it[DatastoreKeys.UserInfo.LEVEL] = value }
    }

    suspend fun setCategories(value: Set<String>) {
        datastore.edit { it[DatastoreKeys.UserInfo.CATEGORIES] = value }
    }

    suspend fun clear() {
        datastore.edit { preferences ->
            DatastoreKeys.UserInfo.KEYS.forEach { key ->
                preferences.remove(key)
            }
        }
    }
}