package com.example.rick_and_morty.ui.character_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty.data.DatabaseClient
import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.DatabaseRepository
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.ui.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase,
    private val databaseInteractor: DatabaseInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<SearchState>()

    fun observeState(): LiveData<SearchState> = stateLiveData

    private var currentPage = 1

    init {
        search()
    }

    fun search() {
        stateLiveData.value = SearchState.Loading
        searchCharactersUseCase.execute(currentPage) { response, errorMessage ->
            CoroutineScope(Dispatchers.IO).launch {
                if (response != null) {
                    Log.d("Response", response.results.toString())
                    if (response.results.isNotEmpty() && response.info.next != null) {
                        stateLiveData.postValue(SearchState.Content(response.results))
                        Log.d("Page", currentPage.toString())
                        currentPage++
                        databaseInteractor.saveCharacters(response.results)
                    }
                } else if (errorMessage != null) {
                    getCharactersFromDatabase(errorMessage)
                }
            }
        }
    }

    private fun getCharactersFromDatabase(errorMessage: String) {
        databaseInteractor.getCharacters {
            CoroutineScope(Dispatchers.IO).launch {
                Log.e("Launches from DB", it.toString())
                val error = SearchState.Error(it, errorMessage)
                stateLiveData.postValue(error)
            }
        }
    }

}