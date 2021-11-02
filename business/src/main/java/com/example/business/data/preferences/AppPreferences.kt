package com.example.business.data.preferences

interface AppPreferences {
    suspend fun savePeriod(period: String)
    suspend fun saveLanguage(language: String)
    suspend fun getPeriod(): String
    suspend fun getLanguage(): String
}