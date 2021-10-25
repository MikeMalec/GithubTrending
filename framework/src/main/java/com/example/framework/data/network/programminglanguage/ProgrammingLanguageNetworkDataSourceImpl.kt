package com.example.framework.data.network.programminglanguage

import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.domain.ProgrammingLanguages
import com.example.framework.data.network.GithubTrendingApi

class ProgrammingLanguageNetworkDataSourceImpl(
    private val api:GithubTrendingApi
):ProgrammingLanguageNetworkDataSource {
    override suspend fun getProgrammingLanguages(): ProgrammingLanguages {
        return api.getProgrammingLanguages()
    }
}