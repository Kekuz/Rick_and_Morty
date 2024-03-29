package com.example.rick_and_morty.ui.character_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.ui.model.RecyclerFragmentEvent
import com.example.rick_and_morty.ui.model.SearchState
import com.example.rick_and_morty.ui.util.SingleLiveEvent
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


    private val eventLiveData = SingleLiveEvent<RecyclerFragmentEvent>()
    fun observeEvent(): LiveData<RecyclerFragmentEvent> = eventLiveData


    private var currentPage = 1


    init {
        search()
    }

    private fun search() {
        stateLiveData.value = SearchState.Loading

        viewModelScope.launch {
            searchCharactersUseCase.execute(currentPage).collect {
                val response = it.first
                val errorMessage = it.second
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

    fun searchNextPage(){
        if (stateLiveData.value is SearchState.Content) {
            search()
        }
        eventLiveData.value = RecyclerFragmentEvent.EndingListEvent
    }

    fun itemClicked(character: Character){
        eventLiveData.value = RecyclerFragmentEvent.ClickRecyclerItemEvent(character)
    }

    private fun getCharactersFromDatabase(errorMessage: String) {
        databaseInteractor.getCharacters {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("Launches from DB", it.toString())
                val error = SearchState.Error(it, errorMessage)
                stateLiveData.postValue(error)
            }
        }
    }
}