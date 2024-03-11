package com.example.rick_and_morty.domain.api

import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun search(page: Int): Single<Resource<CharacterResponse>>
}