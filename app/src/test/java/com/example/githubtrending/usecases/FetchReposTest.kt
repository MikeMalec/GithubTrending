package com.example.githubtrending.usecases

import com.example.business.domain.utils.Resource
import com.example.business.usecases.FetchRepos
import com.example.githubtrending.data.network.repo.FakeRepoNetworkDataSource
import com.example.githubtrending.utils.TestRepoFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import com.google.common.truth.Truth.*
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchReposTest {
    private lateinit var fakeRepoNetworkDataSource: FakeRepoNetworkDataSource
    private lateinit var fetchRepos: FetchRepos

    @Before
    fun init() {
        fakeRepoNetworkDataSource = FakeRepoNetworkDataSource()
        fetchRepos = FetchRepos(fakeRepoNetworkDataSource)
        fakeRepoNetworkDataSource.repos.addAll(TestRepoFactory.createRepos(1..10, "kotlin"))
    }

    @Test
    fun `fetch repos confirm loading success emitted`() = runBlockingTest {
        val results = fetchRepos.fetchRepos("daily", "kotlin").toList()
        assertThat(results.first()).isInstanceOf(Resource.Loading::class.java)
        assertThat(results[1]).isInstanceOf(Resource.Success::class.java)
        (results[1] as Resource.Success).let {
            assertThat(it.data.size).isEqualTo(10)
        }
    }

    @Test
    fun `fetch repos throws error  confirm loading error emitted`() = runBlockingTest {
        fakeRepoNetworkDataSource.throwsError = true
        val results = fetchRepos.fetchRepos("daily", "kotlin").toList()
        assertThat(results.first()).isInstanceOf(Resource.Loading::class.java)
        assertThat(results[1]).isInstanceOf(Resource.Error::class.java)
    }
}