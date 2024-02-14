package com.example.rick_and_morty.domain.impl

import android.util.Log
import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchCharactersUseCaseImpl(private val repository: CharacterRepository) :
    SearchCharactersUseCase {
    override fun execute(
        page: Int,
    ): Flow<Pair<CharacterResponse?, String?>> {
        return repository.search(page).map {
            when (it) {
                is Resource.Success -> {
                    Pair(it.data, null)
                }

                is Resource.Error -> {
                    Pair(null, it.message)
                }
            }
        }

    }
}