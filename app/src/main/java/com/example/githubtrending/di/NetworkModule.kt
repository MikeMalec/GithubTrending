package com.example.githubtrending.di

import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.data.network.repo.RepoNetworkDataSource
import com.example.framework.data.network.GithubTrendingApi
import com.example.framework.data.network.programminglanguage.ProgrammingLanguageNetworkDataSourceImpl
import com.example.framework.data.network.repo.RepoNetworkServiceImpl
import com.example.framework.data.network.repo.RepoNetworkMapper
import com.example.framework.utils.Constants
import com.example.githubtrending.utils.NetworkLogger
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(NetworkLogger())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(Constants.BASE_API_URL)
            .client(okHttpClient)
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
        githubTrendingApi: GithubTrendingApi,
        repoNetworkMapper: RepoNetworkMapper
    ): RepoNetworkDataSource {
        return RepoNetworkServiceImpl(
            api = githubTrendingApi,
            repoNetworkMapper = repoNetworkMapper
        )
    }

    @Singleton
    @Provides
    fun provideProgrammingLanguageNetworkDataSource(api: GithubTrendingApi)
            : ProgrammingLanguageNetworkDataSource {
        return ProgrammingLanguageNetworkDataSourceImpl(api)
    }
}