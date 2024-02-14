package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun search(page: Int): Flow<Resource<CharacterResponse>>
}