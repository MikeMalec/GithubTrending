package com.example.framework.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.business.data.preferences.AppPreferences
import com.example.business.domain.Period
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppPreferencesImpl(private val context: Context) : AppPreferences {
    companion object {
        const val PERIOD_KEY = "PERIOD_KEY"
        const val LANGUAGE_KEY = "LANGUAGE_KEY"
    }

    private val periodKey = stringPreferencesKey(PERIOD_KEY)
    private val languageKey = stringPreferencesKey(
        LANGUAGE_KEY
    )
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override suspend fun savePeriod(period: String) {
        context.dataStore.edit { settings ->
            settings[periodKey] = period
        }
    }

    override suspend fun saveLanguage(language: String) {
        context.dataStore.edit { settings ->
            settings[languageKey] = language
        }
    }

    override suspend fun getPeriod(): String {
        return context.dataStore.data.map { settings ->
            settings[periodKey]
        }.first() ?: Period.Daily.value
    }

    override suspend fun getLanguage(): String {
        return context.dataStore.data.map { settings ->
            settings[languageKey]
        }.first() ?: "Kotlin"
    }
}