package com.example.framework.data.network.repo

import com.example.business.data.network.RepoNetworkDataSource
import com.example.business.domain.Repo
import com.example.framework.data.network.repo.RepoApi
import com.example.framework.data.network.repo.RepoNetworkMapper

class RepoNetworkServiceImpl(
    private val repoApi: RepoApi,
    private val repoNetworkMapper: RepoNetworkMapper
) :
    RepoNetworkDataSource {
    override suspend fun getRepos(period: String, programmingLanguage: String): List<Repo> {
        return repoNetworkMapper.toDomains(
            repoApi.getRepos(
                period = period,
                programmingLanguage = programmingLanguage
            )
        )
    }
}