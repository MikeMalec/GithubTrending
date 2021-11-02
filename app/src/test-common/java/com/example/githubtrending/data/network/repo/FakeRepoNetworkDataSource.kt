package com.example.githubtrending.data.network.repo

import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.domain.Repo

class FakeRepoNetworkDataSource : RepoNetworkDataSource {

    var daily = mutableListOf<Repo>()
    var weekly = mutableListOf<Repo>()
    var monthly = mutableListOf<Repo>()

    var throwsError = false
    private fun checkError() {
        if (throwsError) throw Error()
    }

    override suspend fun getRepos(period: String, programmingLanguage: String): List<Repo> {
        checkError()
        return when (period) {
            "daily" -> daily.filter { it.language == programmingLanguage }
            "weekly" -> weekly.filter { it.language == programmingLanguage }
            else -> monthly.filter { it.language == programmingLanguage }
        }
    }
}