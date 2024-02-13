package com.example.rick_and_morty.domain.model.character

import com.example.rick_and_morty.data.network.dto.characterDto.CharacterDto
import com.example.rick_and_morty.data.network.dto.characterDto.CharacterInfoDto

data class CharacterResponse(
    val info: CharacterInfo,
    val results: List<Character>,
)