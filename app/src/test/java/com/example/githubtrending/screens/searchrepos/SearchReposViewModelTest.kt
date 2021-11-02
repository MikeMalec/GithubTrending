package com.example.githubtrending.screens.searchrepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.business.domain.Period
import com.example.business.usecases.FetchProgrammingLanguages
import com.example.business.usecases.FetchRepos
import com.example.githubtrending.CoroutineRule
import com.example.githubtrending.data.TestRepoFactory
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import com.example.githubtrending.data.network.programminglanguages.FakeProgrammingLanguageNetworkDataSource
import com.example.githubtrending.data.network.repo.FakeRepoNetworkDataSource
import com.example.githubtrending.data.preferences.FakeAppPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchReposViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeProgrammingLanguagesNetworkDataSource: FakeProgrammingLanguageNetworkDataSource
    private lateinit var fakeReposCacheDataSource: FakeRepoCacheDataSource
    private lateinit var fakeRepoNetworkDataSource: FakeRepoNetworkDataSource
    private lateinit var fakePreferences: FakeAppPreferences

    private lateinit var fetchRepos: FetchRepos
    private lateinit var fetchLanguages: FetchProgrammingLanguages

    private lateinit var searchReposViewModel: SearchReposViewModel

    @Before
    fun init() {
        fakeProgrammingLanguagesNetworkDataSource = FakeProgrammingLanguageNetworkDataSource()
        fakeReposCacheDataSource = FakeRepoCacheDataSource()
        fakeRepoNetworkDataSource = FakeRepoNetworkDataSource()
        fakePreferences = FakeAppPreferences()
        fetchRepos = FetchRepos(fakeRepoNetworkDataSource)
        fetchLanguages = FetchProgrammingLanguages(fakeProgrammingLanguagesNetworkDataSource)
        searchReposViewModel = SearchReposViewModel(
            fetchRepos = fetchRepos,
            fetchProgrammingLanguages = fetchLanguages,
            preferences = fakePreferences,
            dispatcher = coroutineRule.dispatcher
        )
    }

    @Test
    fun `fetch programming languages confirm languages set`() = coroutineRule.runBlockingTest {
        val state = searchReposViewModel.state
        assertThat(state.value.languages).isNotEmpty()
    }

    @Test
    fun `fetch repos with filter daily & kotlin`() = coroutineRule.runBlockingTest {
        fakeRepoNetworkDataSource.daily.addAll(TestRepoFactory.createRepos(1..10, "kotlin"))
        fakeRepoNetworkDataSource.daily.addAll(TestRepoFactory.createRepos(1..10, "c"))
        fakeRepoNetworkDataSource.daily.addAll(TestRepoFactory.createRepos(1..10, "c#"))
        searchReposViewModel.setEvent(SearchReposScreenEvent.SetFilters(Period.Daily, "kotlin"))
        searchReposViewModel.setEvent(SearchReposScreenEvent.SearchRepos)
        val state = searchReposViewModel.state.value
        assertThat(state.repos.first().language).isEqualTo("kotlin")
    }

    @Test
    fun `fetch repos with filter weekly & c++ and confirm preferences saved`() = coroutineRule
        .runBlockingTest {
            fakeRepoNetworkDataSource.weekly.addAll(TestRepoFactory.createRepos(1..10, "c++"))
            fakeRepoNetworkDataSource.weekly.addAll(TestRepoFactory.createRepos(1..10, "c"))
            fakeRepoNetworkDataSource.weekly.addAll(TestRepoFactory.createRepos(1..10, "c#"))
            searchReposViewModel.setEvent(SearchReposScreenEvent.SetFilters(Period.Weekly, "c++"))
            searchReposViewModel.setEvent(SearchReposScreenEvent.SearchRepos)
            val state = searchReposViewModel.state.value
            assertThat(state.repos.size).isEqualTo(10)
            assertThat(state.repos.first().language).isEqualTo("c++")
            assertThat(fakePreferences.period).isEqualTo("weekly")
            assertThat(fakePreferences.language).isEqualTo("c++")
        }
}