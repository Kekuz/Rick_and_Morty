package com.example.rick_and_morty.domain.model.character

data class CharacterResponse(
    val info: CharacterInfo,
    val results: List<Character>,
)