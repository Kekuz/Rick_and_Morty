package com.example.rick_and_morty.data.mapper

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
            input.pages,
        )
    }

    private fun characterResultMap(input: List<CharacterDto>): List<Character> {
        return input.map {
            Character(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                it.image,
                it.episode,
                it.url,
                it.created,
            )
        }
    }
}