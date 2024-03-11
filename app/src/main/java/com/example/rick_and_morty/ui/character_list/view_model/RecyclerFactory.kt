package com.example.rick_and_morty.ui.character_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase

class RecyclerFactory(
    private val searchCharactersUseCase: SearchCharactersUseCase,
    private val databaseInteractor: DatabaseInteractor,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecyclerViewModel(searchCharactersUseCase, databaseInteractor) as T
    }

}