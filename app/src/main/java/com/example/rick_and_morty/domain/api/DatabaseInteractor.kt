package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.character.Character

interface DatabaseInteractor {
    fun saveCharacters(characters: List<Character>)

    fun getCharacters(consumer: (List<Character>) -> Unit)
}