package com.example.githubtrending.data.network.programminglanguages

import com.example.business.data.network.programminglanguage.ProgrammingLanguageNetworkDataSource
import com.example.business.domain.ProgrammingLanguages

class FakeProgrammingLanguageNetworkDataSource : ProgrammingLanguageNetworkDataSource {

    var programmingLanguages: ProgrammingLanguages = ProgrammingLanguages().apply {
        addAll(listOf("c,c++,kotlin,java,javascript,php"))
    }

    var throwsException = false

    private fun checkException() {
        if (throwsException) throw Exception()
    }

    override suspend fun getProgrammingLanguages(): ProgrammingLanguages {
        checkException()
        return programmingLanguages
    }
}