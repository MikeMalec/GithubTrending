package com.example.githubtrending.utils

import com.example.business.domain.Repo

object TestRepoFactory {
    fun createRepos(amount: IntRange, language: String = "c"): List<Repo> {
        return amount.map {
            Repo(
                cacheId = it.toLong(),
                author = "$it",
                langColor = "$it",
                description = "$it",
                url = "$it",
                stars = it,
                forks = it,
                language = "$it",
                avatar = "$it",
            )
        }
    }
}