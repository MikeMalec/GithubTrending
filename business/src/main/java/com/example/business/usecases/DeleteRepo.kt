package com.example.business.usecases

import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.domain.Repo
import com.example.business.domain.utils.Resource
import com.example.business.domain.utils.safeCall

class DeleteRepo(private val repoCacheDataSource: RepoCacheDataSource) {
    suspend fun deleteRepo(repo:Repo):Resource<Unit> = safeCall { repoCacheDataSource.deleteRepo(repo) }
}