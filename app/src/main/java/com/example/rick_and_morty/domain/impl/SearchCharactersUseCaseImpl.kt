package com.example.rick_and_morty.domain.impl

import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import io.reactivex.rxjava3.core.Single

class SearchCharactersUseCaseImpl(private val repository: CharacterRepository) :
    SearchCharactersUseCase {
    override fun execute(
        page: Int,
    ): Single<Pair<CharacterResponse?, String?>> {
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