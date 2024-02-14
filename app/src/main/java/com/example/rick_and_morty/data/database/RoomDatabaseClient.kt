package com.example.rick_and_morty.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rick_and_morty.data.DatabaseClient
import com.example.rick_and_morty.data.database.dto.CharacterDatabaseDto
import com.example.rick_and_morty.data.mapper.DatabaseMapper

class RoomDatabaseClient(context: Context) : DatabaseClient {

    //TODO убрать зависимость
    private var database: CharacterDatabase = Room.databaseBuilder(
        context,
        CharacterDatabase::class.java, "character-database"
    ).build()

    override fun save(characters: List<CharacterDatabaseDto>) {
        Log.e("Characters saved in database", characters.toString())
        database.characterDao?.insertAll(characters.map { DatabaseMapper.map(it) })
    }

    override fun get(): List<CharacterDatabaseDto> {
        return database.characterDao?.getAll()?.map { DatabaseMapper.map(it) }
            ?: listOf()
    }
}