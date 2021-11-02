package com.example.githubtrending.usecases

import com.example.business.domain.utils.Resource
import com.example.business.usecases.SaveRepo
import com.example.githubtrending.data.TestRepoFactory
import com.example.githubtrending.data.cache.repo.FakeRepoCacheDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.*

@ExperimentalCoroutinesApi
class SaveRepoTest {
    private lateinit var fakeRepoCacheDataSource: FakeRepoCacheDataSource
    private lateinit var saveRepo: SaveRepo

    @Before
    fun init() {
        fakeRepoCacheDataSource = FakeRepoCacheDataSource()
        saveRepo = SaveRepo(repoCacheDataSource = fakeRepoCacheDataSource)
    }

    @Test
    fun `save repo confirm successfully saved`() = runBlockingTest {
        val result = saveRepo.saveRepo(TestRepoFactory.createRepos(0..1).first())
        assertThat(result).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `save repo throws exception returns error`() = runBlockingTest {
        fakeRepoCacheDataSource.throwsException = true
        val result = saveRepo.saveRepo(TestRepoFactory.createRepos(0..1).first())
        assertThat(result).isInstanceOf(Resource.Error::class.java)
    }
}