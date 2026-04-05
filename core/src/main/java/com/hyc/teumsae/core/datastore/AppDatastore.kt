package com.hyc.teumsae.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDatastore @Inject constructor(
    private val datastore: DataStore<Preferences>
) {
    companion object {
        // Define KEY
    }


}