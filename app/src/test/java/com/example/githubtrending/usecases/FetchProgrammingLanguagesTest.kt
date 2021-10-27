package com.example.githubtrending.usecases

import com.example.business.domain.utils.Resource
import com.example.business.usecases.FetchProgrammingLanguages
import com.example.githubtrending.data.network.programminglanguages.FakeProgrammingLanguageNetworkDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import com.google.common.truth.Truth.*
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchProgrammingLanguagesTest {
    private lateinit var fakeProgrammingLanguageNetworkDataSource: FakeProgrammingLanguageNetworkDataSource
    private lateinit var fetchProgrammingLanguages: FetchProgrammingLanguages

    @Before
    fun init() {
        fakeProgrammingLanguageNetworkDataSource = FakeProgrammingLanguageNetworkDataSource()
        fetchProgrammingLanguages =
            FetchProgrammingLanguages(fakeProgrammingLanguageNetworkDataSource)
    }

    @Test
    fun `fetch languages emits loading success`() = runBlockingTest {
        val results = fetchProgrammingLanguages.fetchProgrammingLanguages().toList()
        assertThat(results.first()).isInstanceOf(Resource.Loading::class.java)
        assertThat(results[1]).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `fetch languages emits loading error`() = runBlockingTest {
        fakeProgrammingLanguageNetworkDataSource.throwsException = true
        val results = fetchProgrammingLanguages.fetchProgrammingLanguages().toList()
        assertThat(results.first()).isInstanceOf(Resource.Loading::class.java)
        assertThat(results[1]).isInstanceOf(Resource.Error::class.java)
    }
}