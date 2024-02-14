package com.example.rick_and_morty.data

import com.example.rick_and_morty.data.database.dto.CharacterDatabaseDto

interface DatabaseClient {
    fun save(characters: List<CharacterDatabaseDto>)

    fun get(): List<CharacterDatabaseDto>

}