package com.example.rick_and_morty.ui.character_list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rick_and_morty.R
import com.example.rick_and_morty.databinding.FragmentRecyclerBinding
import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.ui.character_info.fragment.ItemFragment
import com.example.rick_and_morty.ui.character_list.recycler.ItemAdapter
import com.example.rick_and_morty.ui.character_list.view_model.RecyclerViewModel
import com.example.rick_and_morty.ui.model.SearchState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecyclerFragment : Fragment() {

    private val vm : RecyclerViewModel by viewModels()
    private lateinit var binding: FragmentRecyclerBinding


    private val onEndingList: () -> Unit =
        {
            //TODO Загрузка данных, когда долистываем до конца страницы
        }
    private val onClick: (Character) -> Unit =
        {
            findNavController().navigate(
                R.id.action_recyclerFragment_to_itemFragment,
                ItemFragment.createArgs(it)
            )
        }

    private val characters = mutableListOf<Character>()
    private val itemAdapter = ItemAdapter(characters, onClick, onEndingList)


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

        vm.search()

        vm.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

    }

    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Content -> showContent(state.tracks)
            is SearchState.Empty -> showEmptyContent()
            is SearchState.Error -> showError(state.errorMessage)
            is SearchState.Loading -> showLoading()
        }
    }

    private fun showContent(listCharacters: List<Character>) {
        characters.clear()
        characters.addAll(listCharacters)
        itemAdapter.notifyItemRangeChanged(characters.size, listCharacters.size)
    }

    private fun showEmptyContent() {
        //TODO
    }

    private fun showError(errorMessage: String) {
        //TODO
    }

    private fun showLoading() {
        //TODO
    }


}