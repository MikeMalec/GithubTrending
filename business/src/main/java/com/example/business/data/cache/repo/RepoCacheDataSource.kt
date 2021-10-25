package com.example.business.data.cache.repo

import com.example.business.domain.Repo
import kotlinx.coroutines.flow.Flow

interface RepoCacheDataSource {
    suspend fun saveRepo(repo: Repo): Long
    suspend fun deleteRepo(repo: Repo)
    fun getRepos(): Flow<List<Repo>>
}