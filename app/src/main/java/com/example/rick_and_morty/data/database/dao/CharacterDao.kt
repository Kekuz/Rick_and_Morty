package com.example.rick_and_morty.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rick_and_morty.data.database.model.CharacterDatabaseEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(launches: List<CharacterDatabaseEntity>)
    @Query("SELECT * FROM character_database")
    fun getAll(): List<CharacterDatabaseEntity>?



}