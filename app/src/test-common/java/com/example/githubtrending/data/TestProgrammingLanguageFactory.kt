package com.example.githubtrending.data

import com.example.business.domain.ProgrammingLanguages

object TestProgrammingLanguageFactory {
    fun createLanguages(): ProgrammingLanguages {
        return ProgrammingLanguages().apply {
            addAll(
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
}