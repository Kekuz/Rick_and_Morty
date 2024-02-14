package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface SearchCharactersUseCase {
    fun execute(
        page:Int,
        //consumer: (CharacterResponse?, errorMessage: String?) -> Unit
    ): Flow<Pair<CharacterResponse?, String?>>
}