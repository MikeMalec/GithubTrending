package com.example.githubtrending.screens.searchrepos

import com.example.business.domain.Period
import com.example.business.domain.Repo

sealed class SearchReposScreenEvent {
    object SearchRepos : SearchReposScreenEvent()
    object FetchProgrammingLanguages : SearchReposScreenEvent()
    class SetFilters(val period: Period, val language: String) : SearchReposScreenEvent()
}