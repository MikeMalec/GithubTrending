package com.example.framework.data.cache.repo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.framework.utils.Constants

@Entity(tableName = Constants.REPOS_TABLE)
data class RepoCacheEntity(
    @PrimaryKey(autoGenerate = true)
    var cacheId: Long? = null,
    val author: String,
    val name: String,
    val langColor: String,
    val description: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String,
    val avatar: String
)