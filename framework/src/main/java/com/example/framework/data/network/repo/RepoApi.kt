package com.example.framework.data.network.repo

import com.example.framework.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {
    @GET("repositories")
    suspend fun getRepos(
        @Query("period")
        period: String,
        @Query("language")
        programmingLanguage: String,
        @Query("spokenLanguage")
        language: String = Constants.EN
    ): List<RepoDao>
}