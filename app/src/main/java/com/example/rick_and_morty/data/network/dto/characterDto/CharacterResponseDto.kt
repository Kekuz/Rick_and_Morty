package com.example.rick_and_morty.data.network.dto.characterDto

import com.example.rick_and_morty.data.network.dto.Response

data class CharacterResponseDto(
    val info: CharacterInfoDto,
    val results: List<CharacterDto>,
): Response()