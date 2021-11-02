package com.example.githubtrending.screens.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.business.domain.Repo
import com.example.business.usecases.DeleteRepo
import com.example.business.usecases.SaveRepo
import com.example.githubtrending.CoroutineRule
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import com.example.githubtrending.screens.MainActivity
import com.example.githubtrending.utils.TestRepoFactory
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
class RepoViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var fakeRepoCacheDataSource: FakeRepoCacheDataSource
    private lateinit var saveRepo: SaveRepo
    private lateinit var deleteRepo: DeleteRepo

    private lateinit var repoViewModel: RepoViewModel
    private lateinit var repo: Repo

    @Before
    fun init() {
        repo = TestRepoFactory.createRepos(0..1).first()
        MainActivity.repo = repo
        fakeRepoCacheDataSource = FakeRepoCacheDataSource()
        saveRepo = SaveRepo(fakeRepoCacheDataSource)
        deleteRepo = DeleteRepo(fakeRepoCacheDataSource)
        repoViewModel = RepoViewModel(
            saveRepo = saveRepo,
            deleteRepo = deleteRepo,
            dispatcher = coroutineRule.dispatcher
        )
    }

    @Test
    fun `start with saved repo delete confirm deleted`() = coroutineRule.runBlockingTest {
        fakeRepoCacheDataSource.repos.add(repo)
        repoViewModel.setEvent(RepoScreenEvent.DeleteRepo)
        val state = repoViewModel.state.value
        assertThat(state.isSaved).isFalse()
        assertThat(fakeRepoCacheDataSource.repos.isEmpty())
    }

    @Test
    fun `start with not saved repo save confirm saved`() = coroutineRule.runBlockingTest {
        repoViewModel.setEvent(RepoScreenEvent.SaveRepo)
        val state = repoViewModel.state.value
        assertThat(state.isSaved).isTrue()
        assertThat(fakeRepoCacheDataSource.repos).contains(repo)
    }

}