package com.example.framework.data.network.repo

import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.domain.Repo
import com.example.framework.data.network.GithubTrendingApi

class RepoNetworkServiceImpl(
    private val api: GithubTrendingApi,
    private val repoNetworkMapper: RepoNetworkMapper
) :
    RepoNetworkDataSource {
    override suspend fun getRepos(period: String, programmingLanguage: String): List<Repo> {
        return repoNetworkMapper.toDomains(
            api.getRepos(
                period = period,
                programmingLanguage = programmingLanguage
            )
        )
    }
}