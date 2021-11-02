package com.example.githubtrending.data.preferences

import com.example.business.data.preferences.AppPreferences

class FakeAppPreferences : AppPreferences {

    var period: String = ""

    var language: String = ""

    override suspend fun savePeriod(period: String) {
        this.period = period
    }

    override suspend fun saveLanguage(language: String) {
        this.language = language
    }

    override suspend fun getPeriod(): String {
        return period
    }

    override suspend fun getLanguage(): String {
        return language
    }
}