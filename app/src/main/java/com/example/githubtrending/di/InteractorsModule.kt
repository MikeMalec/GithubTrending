package com.example.githubtrending.di

import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideFetchRepos(repoNetworkDataSource: RepoNetworkDataSource): FetchRepos {
        return FetchRepos(repoNetworkDataSource = repoNetworkDataSource)
    }

    @Singleton
    @Provides
    fun provideFetchProgrammingLanguages(programmingLanguageNetworkDataSource: ProgrammingLanguageNetworkDataSource): FetchProgrammingLanguages {
        return FetchProgrammingLanguages(programmingLanguageNetworkDataSource = programmingLanguageNetworkDataSource)
    }

    @Singleton
    @Provides
    fun provideSaveRepo(repoCacheDataSource: RepoCacheDataSource): SaveRepo {
        return SaveRepo(repoCacheDataSource = repoCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideDeleteRepo(repoCacheDataSource: RepoCacheDataSource): DeleteRepo {
        return DeleteRepo(repoCacheDataSource = repoCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideGetRepos(repoCacheDataSource: RepoCacheDataSource): GetRepos {
        return GetRepos(repoCacheDataSource = repoCacheDataSource)
    }
}