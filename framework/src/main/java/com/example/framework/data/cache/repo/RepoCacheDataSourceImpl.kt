package com.example.framework.data.cache.repo

import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.domain.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepoCacheDataSourceImpl(
    private val repoDao: RepoDao,
    private val repoCacheMapper: RepoCacheMapper
) : RepoCacheDataSource {
    override suspend fun saveRepo(repo: Repo): Long {
        return repoDao.saveRepo(repoCacheMapper.fromDomain(repo))
    }

    override suspend fun deleteRepo(repo: Repo) {
        repoDao.deleteRepo(repoCacheMapper.fromDomain(repo))
    }

    override fun getRepos(): Flow<List<Repo>> {
        return repoDao.getRepos().map { repoCacheMapper.toDomains(it) }
    }
}