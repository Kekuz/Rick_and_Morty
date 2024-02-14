package com.example.rick_and_morty.data.mapper

import com.example.rick_and_morty.data.database.dto.CharacterDatabaseDto
import com.example.rick_and_morty.data.network.dto.characterDto.CharacterDto
import com.example.rick_and_morty.data.network.dto.characterDto.CharacterInfoDto
import com.example.rick_and_morty.data.network.dto.characterDto.CharacterResponseDto
import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.domain.model.character.CharacterInfo
import com.example.rick_and_morty.domain.model.character.CharacterResponse

object CharacterMapper {

    fun map(input: CharacterResponseDto): CharacterResponse {
        return CharacterResponse(
            characterInfoMap(input.info),
            characterResultMap(input.results)
        )
    }

    private fun characterInfoMap(input: CharacterInfoDto): CharacterInfo {
        return CharacterInfo(
            input.count,
            input.next,
        )
    }

    private fun characterResultMap(input: List<CharacterDto>): List<Character> {
        return input.map {
            Character(
                it.id ?: -1,
                it.name ?: "",
                it.status ?: "",
                it.species ?: "",
                it.type ?: "",
                it.gender ?: "",
                it.image ?: "",
                it.created.formatDate(),
            )
        }
    }

    private fun String?.formatDate(): String =
        if (this == null) {
            ""
        } else {
            val indexOfT = this.indexOf('T')
            this.substring(0, indexOfT)
        }

    fun mapToDB(input: List<Character>): List<CharacterDatabaseDto> {
        return input.map {
            CharacterDatabaseDto(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                it.image,
                it.created,
            )
        }
    }

    fun mapFromDB(input: List<CharacterDatabaseDto>): List<Character> {
        return input.map {
            Character(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                it.image,
                it.created,
            )
        }
    }

}