package com.example.business.data.network

import com.example.business.domain.Repo

interface RepoNetworkDataSource {
    suspend fun getRepos(period: String, programmingLanguage: String): List<Repo>
}