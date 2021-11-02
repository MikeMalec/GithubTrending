package com.example.framework.data.cache.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert
    fun saveRepo(repoCacheEntity: RepoCacheEntity): Long

    @Delete
    fun deleteRepo(repoCacheEntity: RepoCacheEntity)

    @Query("SELECT * FROM repos")
    fun getRepos(): Flow<List<RepoCacheEntity>>
}