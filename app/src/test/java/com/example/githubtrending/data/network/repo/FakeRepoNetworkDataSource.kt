package com.example.githubtrending.data.network.repo

import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.domain.Repo

class FakeRepoNetworkDataSource : RepoNetworkDataSource {

    var repos = mutableListOf<Repo>()

    var throwsError = false
    private fun checkError() {
        if (throwsError) throw Error()
    }

    override suspend fun getRepos(period: String, programmingLanguage: String): List<Repo> {
        checkError()
        return repos.filter { it.language == programmingLanguage }
    }
}