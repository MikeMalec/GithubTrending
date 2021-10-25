package com.example.framework.data.cache.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert
    suspend fun saveRepo(repoCacheEntity: RepoCacheEntity): Long

    @Delete
    suspend fun deleteRepo(repoCacheEntity: RepoCacheEntity)

    @Query("SELECT * FROM repos")
    fun getRepos(): Flow<List<RepoCacheEntity>>
}