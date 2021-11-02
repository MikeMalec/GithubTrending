package com.example.githubtrending.di

import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.githubtrending.data.network.programminglanguages.FakeProgrammingLanguageNetworkDataSource
import com.example.githubtrending.data.network.repo.FakeRepoNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    replaces = [NetworkModule::class],
    components = [SingletonComponent::class]
)
object TestNetworkModule {
    @Singleton
    @Provides
    fun provideRepoNetworkService(): RepoNetworkDataSource {
        return FakeRepoNetworkDataSource()
    }

    @Singleton
    @Provides
    fun provideProgrammingLanguageNetworkDataSource(): ProgrammingLanguageNetworkDataSource {
        return FakeProgrammingLanguageNetworkDataSource()
    }
}