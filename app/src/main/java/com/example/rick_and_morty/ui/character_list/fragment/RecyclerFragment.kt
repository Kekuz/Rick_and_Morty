package com.example.rick_and_morty.ui.character_list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rick_and_morty.R
import com.example.rick_and_morty.databinding.FragmentRecyclerBinding
import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.ui.character_info.fragment.ItemFragment
import com.example.rick_and_morty.ui.character_list.recycler.ItemAdapter
import com.example.rick_and_morty.ui.character_list.view_model.RecyclerViewModel
import com.example.rick_and_morty.ui.model.RecyclerFragmentEvent
import com.example.rick_and_morty.ui.model.SearchState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecyclerFragment : Fragment() {

    private val vm: RecyclerViewModel by viewModels()
    private lateinit var binding: FragmentRecyclerBinding

    private val onEndingList: () -> Unit = {
            vm.searchNextPage()
        }

    private val onClick: (Character) -> Unit = {
            vm.itemClicked(it)
        }

    private val itemAdapter = ItemAdapter(onClick, onEndingList)


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

        vm.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        vm.observeEvent().observe(viewLifecycleOwner){
            eventHandler(it)
        }

    }

    private fun eventHandler(event: RecyclerFragmentEvent){
        when(event){
            is RecyclerFragmentEvent.ClickRecyclerItemEvent -> navigateToDetails(event.character)
            is RecyclerFragmentEvent.EndingListEvent -> {/*Nothing*/}
        }
    }

    private fun navigateToDetails(character: Character){
        findNavController().navigate(
            R.id.action_recyclerFragment_to_itemFragment,
            ItemFragment.createArgs(character)
        )
    }


    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Content -> showContent(state.characters)
            is SearchState.Error -> showError(state.characters, state.errorMessage)
            is SearchState.Loading -> showLoading()
        }
    }

    private fun showContent(listCharacters: List<Character>) = with(binding) {
        itemAdapter.addCharacters(listCharacters)
        tvError.isVisible = false
        progressBar.isVisible = false
        recyclerView.isVisible = true
    }

    private fun showError(listFromDB: List<Character>, errorMessage: String) = with(binding) {
        itemAdapter.clearCharacters()
        itemAdapter.addCharacters(listFromDB)
        if (listFromDB.isEmpty()) {
            recyclerView.isVisible = false
            tvError.isVisible = true
            tvError.text = errorMessage
            progressBar.isVisible = false
        } else {
            recyclerView.isVisible = true
            tvError.isVisible = false
            progressBar.isVisible = false
        }

    }

    private fun showLoading() {
        //Можно использовать для управления progress bar, если продумать xml экрана загрузки
    }


}