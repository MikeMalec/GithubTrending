package com.example.githubtrending.di

import android.content.Context
import androidx.room.Room
import com.example.business.data.cache.repo.RepoCacheDataSource
import com.example.framework.data.cache.repo.Database
import com.example.framework.data.cache.repo.RepoCacheDataSourceImpl
import com.example.framework.data.cache.repo.RepoCacheMapper
import com.example.framework.data.cache.repo.RepoDao
import com.example.framework.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, Constants.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(database: Database): RepoDao {
        return database.getRepoDao()
    }

    @Singleton
    @Provides
    fun provideRepoCacheMapper(): RepoCacheMapper {
        return RepoCacheMapper()
    }

    @Singleton
    @Provides
    fun provideRepoCacheDataSource(
        repoDao: RepoDao,
        repoCacheMapper: RepoCacheMapper
    ): RepoCacheDataSource {
        return RepoCacheDataSourceImpl(repoDao = repoDao, repoCacheMapper = repoCacheMapper)
    }
}