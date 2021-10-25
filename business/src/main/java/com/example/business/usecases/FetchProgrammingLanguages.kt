package com.example.business.usecases

import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.domain.ProgrammingLanguages
import com.example.business.domain.utils.Resource
import com.example.business.domain.utils.Resource.Loading
import com.example.business.domain.utils.safeCall
import kotlinx.coroutines.flow.flow

class FetchProgrammingLanguages(private val programmingLanguageNetworkDataSource: ProgrammingLanguageNetworkDataSource) {
    fun fetchProgrammingLanguages() = flow<Resource<ProgrammingLanguages>> {
        emit(Loading)
        emit(safeCall { programmingLanguageNetworkDataSource.getProgrammingLanguages() })
    }
}