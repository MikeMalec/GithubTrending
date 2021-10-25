package com.example.framework.data.cache.repo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RepoCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getRepoDao(): RepoDao
}