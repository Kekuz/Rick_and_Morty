package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.character.CharacterResponse
import io.reactivex.rxjava3.core.Single

interface SearchCharactersUseCase {
    fun execute(
        page: Int
    ): Single<Pair<CharacterResponse?, String?>>
}