package com.example.rick_and_morty.data.database

import android.util.Log
import com.example.rick_and_morty.data.DatabaseClient
import com.example.rick_and_morty.data.database.dto.CharacterDatabaseDto
import com.example.rick_and_morty.data.mapper.DatabaseMapper

class RoomDatabaseClient(private val database: CharacterDatabase) : DatabaseClient {


    override fun save(characters: List<CharacterDatabaseDto>) {
        Log.d("Characters saved in database", characters.toString())
        database.characterDao?.insertAll(characters.map { DatabaseMapper.map(it) })
    }

    override fun get(): List<CharacterDatabaseDto> {
        return database.characterDao?.getAll()?.map { DatabaseMapper.map(it) }
            ?: listOf()
    }
}