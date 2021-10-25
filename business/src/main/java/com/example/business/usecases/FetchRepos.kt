package com.example.business.usecases

import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.domain.Repo
import com.example.business.domain.utils.Resource
import com.example.business.domain.utils.Resource.Loading
import com.example.business.domain.utils.safeCall
import kotlinx.coroutines.flow.flow

class FetchRepos(private val repoNetworkDataSource: RepoNetworkDataSource) {
    fun fetchRepos(period: String, programmingLanguage: String) = flow<Resource<List<Repo>>> {
        emit(Loading)
        emit(safeCall {
            repoNetworkDataSource.getRepos(
                period = period,
                programmingLanguage = programmingLanguage
            )
        })
    }
}