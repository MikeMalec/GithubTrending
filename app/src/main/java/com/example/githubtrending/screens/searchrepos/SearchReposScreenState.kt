package com.example.githubtrending.screens.searchrepos

import com.example.business.domain.Period
import com.example.business.domain.Repo
import com.example.githubtrending.utils.CloneableState

data class SearchReposScreenState(
    var period: Period = Period.Daily,
    var language: String = "",
    var languages: List<String> = listOf(""),
    var repos: List<Repo> = listOf(),
    var loading: Boolean = false,
    var error: String? = null
) : CloneableState<SearchReposScreenState> {
    override fun clone(): SearchReposScreenState {
        return this.copy()
    }
}