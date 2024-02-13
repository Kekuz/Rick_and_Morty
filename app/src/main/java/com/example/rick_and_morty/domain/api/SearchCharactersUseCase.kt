package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.character.CharacterResponse

interface SearchCharactersUseCase {
    fun execute(
        page:Int,
        consumer: (CharacterResponse?, errorMessage: String?) -> Unit
    )
}