package com.example.githubtrending.data.cache.repo

import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.domain.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception
import kotlin.random.Random

class FakeRepoCacheDataSource : RepoCacheDataSource {

    var repos = mutableListOf<Repo>()

    var throwsException = false

    private fun checkException() {
        if (throwsException) throw  Exception()
    }

    override suspend fun saveRepo(repo: Repo): Long {
        checkException()
        val id = Random.nextLong()
        repo.cacheId = id
        repos.add(repo)
        return id
    }

    override suspend fun deleteRepo(repo: Repo) {
        checkException()
        repos.remove(repo)
    }

    override fun getRepos(): Flow<List<Repo>> {
        return flowOf(repos)
    }
}