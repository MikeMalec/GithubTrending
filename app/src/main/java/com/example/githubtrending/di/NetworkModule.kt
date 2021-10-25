package com.example.githubtrending.di

import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.business.data.network.RepoNetworkDataSourceImpl
import com.example.business.data.network.RepoNetworkService
import com.example.framework.data.network.GithubTrendingApi
import com.example.framework.data.network.repo.RepoNetworkServiceImpl
import com.example.framework.data.network.repo.RepoNetworkMapper
import com.example.framework.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(Constants.BASE_API_URL)
    }

    @Singleton
    @Provides
    fun provideRepoApi(retrofit: Retrofit.Builder): GithubTrendingApi {
        return retrofit.build().create(GithubTrendingApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepoNetworkMapper(): RepoNetworkMapper {
        return RepoNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideRepoNetworkService(
        repoApi: RepoApi,
        repoNetworkMapper: RepoNetworkMapper
    ): RepoNetworkService {
        return RepoNetworkServiceImpl(repoApi = repoApi, repoNetworkMapper = repoNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideRepoNetworkDataSource(repoNetworkService: RepoNetworkService): RepoNetworkDataSource {
        return RepoNetworkDataSourceImpl(repoNetworkService = repoNetworkService)
    }
}