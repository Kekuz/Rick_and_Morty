package com.example.rick_and_morty.domain.impl

import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.DatabaseRepository
import com.example.rick_and_morty.domain.model.character.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseInteractorImpl(private val repository: DatabaseRepository) : DatabaseInteractor {
    override fun saveCharacters(characters: List<Character>) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveCharacters(characters)
        }
    }

    override fun getCharacters(consumer: (List<Character>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            consumer.invoke(repository.getCharacters())
        }
    }
}