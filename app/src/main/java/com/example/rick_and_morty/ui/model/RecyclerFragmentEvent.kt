package com.example.rick_and_morty.ui.model

import com.example.rick_and_morty.domain.model.character.Character

sealed interface RecyclerFragmentEvent {
    data class ClickRecyclerItemEvent(val character: Character) : RecyclerFragmentEvent
    data object EndingListEvent : RecyclerFragmentEvent
}