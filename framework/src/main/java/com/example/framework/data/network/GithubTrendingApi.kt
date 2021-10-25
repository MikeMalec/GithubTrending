package com.example.framework.data.network

import com.example.business.domain.ProgrammingLanguages
import com.example.framework.data.network.repo.RepoDao
import com.example.framework.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingApi {
    @GET("repositories")
    suspend fun getRepos(
        @Query("period")
        period: String,
        @Query("language")
        programmingLanguage: String,
        @Query("spokenLanguage")
        language: String = Constants.EN
    ): List<RepoDao>

    @GET("languages")
    suspend fun getProgrammingLanguages(): ProgrammingLanguages
}