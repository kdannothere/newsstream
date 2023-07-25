package com.kdannothere.newsstream.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data")

    suspend fun saveThemeSetting(context: Context, isThemeDark: Boolean) {
        context.dataStore.edit { data ->
            data[Keys.isThemeDarkKey] = isThemeDark
        }
    }

    suspend fun loadThemeSetting(context: Context): Boolean? {
        return context.dataStore.data
            .map { preferences ->
                preferences[Keys.isThemeDarkKey]
            }
            .first()
    }
}

object Keys {
    val isThemeDarkKey = booleanPreferencesKey("isThemeDark")
}