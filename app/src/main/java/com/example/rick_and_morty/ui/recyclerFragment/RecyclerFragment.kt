package com.example.rick_and_morty.ui.recyclerFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rick_and_morty.R
import com.example.rick_and_morty.data.NetworkClient
import com.example.rick_and_morty.data.network.RetrofitNetworkClient
import com.example.rick_and_morty.data.network.RickAndMortyAPI
import com.example.rick_and_morty.data.repository.CharacterRepositoryImpl
import com.example.rick_and_morty.databinding.FragmentRecyclerBinding
import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.impl.SearchCharactersUseCaseImpl
import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerBinding


    private val onEndingList: () -> Unit =
        {
            //TODO Загрузка данных, когда долистываем до конца страницы
        }
    private val onClick: (Character) -> Unit =
        {
            findNavController().navigate(
                R.id.action_recyclerFragment_to_itemFragment,
                //Тут еще бандл прокинуть
            )
        }

    private val characters = mutableListOf<Character>()
    private val itemAdapter = ItemAdapter(characters, onClick, onEndingList)

    private val mockList = listOf(
        Character(
            1,
            "Рик",
            "Жив",
            "Человек",
            "-",
            "Мужчина",
            "-",
            emptyList(),
            "-",
            "-",
        )

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = itemAdapter

        val client = RetrofitNetworkClient(
            requireContext(), Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyAPI::class.java)
        )

        SearchCharactersUseCaseImpl(
            CharacterRepositoryImpl(
                client,
                requireContext()
            )
        ).execute(1) { response, errorMessage ->
            CoroutineScope(Dispatchers.IO).launch {
                if (response != null) {
                    Log.d("Response", response.results.toString())
                    if (response.results.isNotEmpty()) {
                        withContext(Dispatchers.Main) {
                            showContent(response.results)
                        }
                    }
                } /*else if (errorMessage != null) {
                    stateLiveData.postValue(SearchState.Error(errorMessage))
                    lastRequest = text
                }*/
            }
        }
        //showContent(mockList)

    }

    private fun showContent(listCharacters: List<Character>) {
        characters.clear()
        characters.addAll(listCharacters)
        itemAdapter.notifyItemRangeChanged(characters.size, listCharacters.size)
    }
}