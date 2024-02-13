package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse

interface CharacterRepository {
    fun search(page: Int): Resource<CharacterResponse>
}