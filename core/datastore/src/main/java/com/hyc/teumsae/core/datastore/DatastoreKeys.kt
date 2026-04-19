package com.hyc.teumsae.core.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

internal object DatastoreKeys {
    object UserInfo {
        private const val PREFIX = "user_info"

        val START_STATION = stringPreferencesKey("${PREFIX}.start_station")
        val END_STATION = stringPreferencesKey("${PREFIX}.end_station")
        val LEVEL = intPreferencesKey("${PREFIX}.level")
        val CATEGORIES = stringSetPreferencesKey("${PREFIX}.categories")

        val KEYS: List<Preferences.Key<*>> = listOf(START_STATION, END_STATION, LEVEL, CATEGORIES)
    }
}