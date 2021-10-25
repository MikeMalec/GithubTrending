package com.example.framework.data.cache.repo

import com.example.business.domain.Repo

class RepoCacheMapper {
    fun toDomain(repoCacheEntity: RepoCacheEntity): Repo {
        return Repo(
            cacheId = repoCacheEntity.cacheId,
            author = repoCacheEntity.author,
            langColor = repoCacheEntity.langColor,
            description = repoCacheEntity.description,
            url = repoCacheEntity.url,
            stars = repoCacheEntity.stars,
            forks = repoCacheEntity.forks,
            language = repoCacheEntity.language,
            avatar = repoCacheEntity.avatar,
        )
    }

    fun toDomains(repoCacheEntities: List<RepoCacheEntity>): List<Repo> {
        return repoCacheEntities.map { toDomain(it) }
    }

    fun fromDomain(repo: Repo): RepoCacheEntity {
        return RepoCacheEntity(
            cacheId = repo.cacheId,
            author = repo.author,
            langColor = repo.langColor,
            description = repo.description,
            url = repo.url,
            stars = repo.stars,
            forks = repo.forks,
            language = repo.language,
            avatar = repo.avatar,
        )
    }
}