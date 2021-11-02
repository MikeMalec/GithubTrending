package com.example.githubtrending.usecases

import com.example.business.domain.utils.Resource
import com.example.business.usecases.DeleteRepo
import com.example.githubtrending.data.TestRepoFactory
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import com.google.common.truth.Truth.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteRepoTest {
    private lateinit var fakeRepoCacheDataSource: FakeRepoCacheDataSource
    private lateinit var deleteRepo: DeleteRepo

    @Before
    fun init() {
        fakeRepoCacheDataSource = FakeRepoCacheDataSource()
        deleteRepo = DeleteRepo(fakeRepoCacheDataSource)
    }

    @Test
    fun `delete repo confirm repo deleted`() = runBlockingTest {
        val repo = TestRepoFactory.createRepos(0..1).first()
        fakeRepoCacheDataSource.repos.add(repo)
        val result = deleteRepo.deleteRepo(repo)
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(fakeRepoCacheDataSource.repos).isEmpty()
    }

    @Test
    fun `delete repo throws error`() = runBlockingTest {
        val repo = TestRepoFactory.createRepos(0..1).first()
        fakeRepoCacheDataSource.repos.add(repo)
        fakeRepoCacheDataSource.throwsException = true
        val result = deleteRepo.deleteRepo(repo)
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(fakeRepoCacheDataSource.repos).isNotEmpty()
    }
}