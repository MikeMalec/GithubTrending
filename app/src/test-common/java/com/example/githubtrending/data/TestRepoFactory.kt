package com.example.githubtrending.data

import com.example.business.domain.Repo

object TestRepoFactory {
    fun createRepos(amount: IntRange, language: String = "c"): List<Repo> {
        return amount.map {
            Repo(
                cacheId = it.toLong(),
                name = it.toString(),
                author = "$it",
                langColor = "0xFFff5722",
                description = "$it",
                url = "$it",
                stars = it,
                forks = it,
                language = "$language",
                avatar = "$it",
            )
        }
    }
}