package com.example.rick_and_morty.ui.recyclerFragment

import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.ItemViewBinding
import com.example.rick_and_morty.domain.model.character.Character

class ItemViewHolder(private val binding: ItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Character, onClick: (Character) -> Unit,) = with(binding) {

        tvFirst.text = model.name

        itemView.setOnClickListener{
            onClick.invoke(model)
        }
    }

}