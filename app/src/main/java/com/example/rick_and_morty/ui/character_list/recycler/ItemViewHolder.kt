package com.example.rick_and_morty.ui.character_list.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rick_and_morty.R
import com.example.rick_and_morty.databinding.ItemViewBinding
import com.example.rick_and_morty.domain.model.character.Character

class ItemViewHolder(private val binding: ItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Character, onClick: (Character) -> Unit,) = with(binding) {

        tvName.text = model.name
        tvSpecies.text = model.species

        itemView.setOnClickListener{
            onClick.invoke(model)
        }
    }

}