package com.example.rick_and_morty.domain.impl

import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchCharactersUseCaseImpl(private val repository: CharacterRepository) :
    SearchCharactersUseCase {
    override fun execute(
        page: Int,
        consumer: (CharacterResponse?, errorMessage: String?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch{

            when (val resource = repository.search(page)) {
                is Resource.Success -> {
                    consumer.invoke(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.invoke(null, resource.message)
                }
            }
        }
    }
}