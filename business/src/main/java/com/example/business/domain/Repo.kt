package com.example.business.domain

data class Repo(
    var cacheId: Long? = null,
    val author: String,
    val langColor: String,
    val description: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String,
    val avatar: String
)