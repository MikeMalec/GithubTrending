package com.example.githubtrending.data

import com.example.business.domain.Period
import com.example.business.domain.Repo

object TestRepoFactory {
    fun createRepos(
        amount: IntRange,
        language: String = "c",
        period: Period = Period.Daily
    ): List<Repo> {
        return amount.map {
            Repo(
                cacheId = it.toLong(),
                name = language,
                author = language,
                langColor = "#A97BFF",
                description = "${period.value} Project in $language",
                url = "$it",
                stars = it,
                forks = it,
                language = language,
                avatar = "$it",
            )
        }
    }
}