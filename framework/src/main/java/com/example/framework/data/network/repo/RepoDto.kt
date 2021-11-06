package com.example.framework.data.network.repo

data class RepoDto(
    val author: String,
    val name:String,
    val langColor: String,
    val description: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String,
    val avatar: String
)

