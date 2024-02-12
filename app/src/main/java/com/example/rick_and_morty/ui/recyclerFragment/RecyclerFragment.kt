package com.example.rick_and_morty.ui.recyclerFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rick_and_morty.R
import com.example.rick_and_morty.databinding.FragmentRecyclerBinding
import com.example.rick_and_morty.domain.model.Character
import com.example.rick_and_morty.ui.ItemFragment

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
        Character("name"),
        Character("name2"),
        Character("name3"),
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

        showContent(mockList)

    }

    private fun showContent(listCharacters: List<Character>) {
        characters.clear()
        characters.addAll(listCharacters)
        itemAdapter.notifyItemRangeChanged(characters.size, listCharacters.size)
    }
}