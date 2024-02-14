package com.example.rick_and_morty.data.repository

import com.example.rick_and_morty.data.DatabaseClient
import com.example.rick_and_morty.data.mapper.CharacterMapper
import com.example.rick_and_morty.domain.api.DatabaseRepository
import com.example.rick_and_morty.domain.model.character.Character

class DatabaseRepositoryImpl(private val databaseClient: DatabaseClient) : DatabaseRepository {
    override fun saveCharacters(characters: List<Character>) {
        databaseClient.save(CharacterMapper.mapToDB(characters))
    }

    override fun getCharacters(): List<Character> {
        return CharacterMapper.mapFromDB(databaseClient.get())
    }


}