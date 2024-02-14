package com.example.rick_and_morty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rick_and_morty.data.database.dao.CharacterDao
import com.example.rick_and_morty.data.database.model.CharacterDatabaseEntity

@Database(
    entities = [CharacterDatabaseEntity::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao?
}