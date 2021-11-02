package com.example.githubtrending.di

import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    replaces = [CacheModule::class],
    components = [SingletonComponent::class]
)
object TestCacheModule {
    @Singleton
    @Provides
    fun provideRepoCacheDataSource(): RepoCacheDataSource {
        return FakeRepoCacheDataSource()
    }
}