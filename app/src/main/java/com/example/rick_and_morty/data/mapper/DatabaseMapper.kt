package com.example.rick_and_morty.data.mapper

import com.example.rick_and_morty.data.database.dto.CharacterDatabaseDto
import com.example.rick_and_morty.data.database.model.CharacterDatabaseEntity

object DatabaseMapper {
    fun map(input: CharacterDatabaseDto): CharacterDatabaseEntity {
        return CharacterDatabaseEntity(
            input.id,
            input.name,
            input.status,
            input.species,
            input.type,
            input.gender,
            input.image,
            input.created,
        )
    }

    fun map(input: CharacterDatabaseEntity): CharacterDatabaseDto {
        return CharacterDatabaseDto(
            input.id,
            input.name,
            input.status,
            input.species,
            input.type,
            input.gender,
            input.image,
            input.created,
        )
    }
}