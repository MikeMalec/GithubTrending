package com.example.githubtrending.usecases

import com.example.business.usecases.GetRepos
import com.example.githubtrending.data.TestRepoFactory
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import com.google.common.truth.Truth.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetReposTest {
    private lateinit var fakeRepoCacheDataSource: FakeRepoCacheDataSource
    private lateinit var getRepo: GetRepos

    @Before
    fun init() {
        fakeRepoCacheDataSource = FakeRepoCacheDataSource()
        getRepo = GetRepos(fakeRepoCacheDataSource)
        fakeRepoCacheDataSource.repos.addAll(TestRepoFactory.createRepos(1..10))
    }

    @Test
    fun `get repos confirm success`() = runBlockingTest {
        val repos = getRepo.getRepos().toList()
        assertThat(repos.first()).isEqualTo(fakeRepoCacheDataSource.repos)
    }
}