package com.example.business.usecases

import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.domain.Repo
import kotlinx.coroutines.flow.Flow

class GetRepos(private val repoCacheDataSource: RepoCacheDataSource) {
    fun getRepos(): Flow<List<Repo>> = repoCacheDataSource.getRepos()
}