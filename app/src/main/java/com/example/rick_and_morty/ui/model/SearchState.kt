package com.example.rick_and_morty.ui.model

import com.example.rick_and_morty.domain.model.character.Character

sealed interface SearchState {
    data object Loading : SearchState
    data class Content(
        val tracks: List<Character>
    ) : SearchState

    data class Error(
        val errorMessage: String
    ) : SearchState


}