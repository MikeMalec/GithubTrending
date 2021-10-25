package com.example.business.data.network.programminglanguage

import com.example.business.domain.ProgrammingLanguages

interface ProgrammingLanguageNetworkDataSource {
    suspend fun getProgrammingLanguages(): ProgrammingLanguages
}