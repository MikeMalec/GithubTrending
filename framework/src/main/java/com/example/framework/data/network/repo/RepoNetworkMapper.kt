package com.example.framework.data.network.repo

import com.example.business.domain.Repo

class RepoNetworkMapper {
    fun toDomain(repoDao: RepoDao): Repo {
        return Repo(
            cacheId = null,
            author = repoDao.author,
            langColor = repoDao.langColor,
            description = repoDao.description,
            url = repoDao.url,
            stars = repoDao.stars,
            forks = repoDao.forks,
            language = repoDao.language,
            avatar = repoDao.avatar,
        )
    }

    fun toDomains(repoDaos: List<RepoDao>): List<Repo> {
        return repoDaos.map { toDomain(it) }
    }
}