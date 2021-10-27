package com.example.githubtrending.utils

import com.example.business.domain.ProgrammingLanguages

object TestProgrammingLanguageFactory {
    fun createLanguages(): ProgrammingLanguages {
        return ProgrammingLanguages(
            listOf(
                "kotlin",
                "java",
                "c",
                "c++",
                "javascript"
            )
        )
    }
}