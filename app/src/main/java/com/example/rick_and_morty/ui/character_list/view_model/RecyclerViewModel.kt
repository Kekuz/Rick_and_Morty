package com.example.rick_and_morty.ui.character_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.ui.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

    private val stateLiveData = MutableLiveData<SearchState>()

    fun observeState(): LiveData<SearchState> = stateLiveData


    fun search() {
        searchCharactersUseCase.execute(1) { response, errorMessage ->
            CoroutineScope(Dispatchers.IO).launch {
                if (response != null) {
                    Log.d("Response", response.results.toString())
                    if (response.results.isNotEmpty()) {
                        stateLiveData.postValue(SearchState.Content(response.results))
                    } else {
                        stateLiveData.postValue(SearchState.Empty)
                    }
                } else if (errorMessage != null) {
                    stateLiveData.postValue(SearchState.Error(errorMessage))
                }
            }
        }
    }


}