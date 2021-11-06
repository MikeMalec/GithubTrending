package com.example.framework.data.network.repo

import com.example.business.domain.Repo

class RepoNetworkMapper {
    fun toDomain(repoDto: RepoDto): Repo {
        return Repo(
            cacheId = null,
            name = repoDto.name,
            author = repoDto.author,
            langColor = repoDto.langColor,
            description = repoDto.description,
            url = repoDto.url,
            stars = repoDto.stars,
            forks = repoDto.forks,
            language = repoDto.language,
            avatar = repoDto.avatar,
        )
    }

    fun toDomains(repoDtos: List<RepoDto>): List<Repo> {
        return repoDtos.map { toDomain(it) }
    }
}