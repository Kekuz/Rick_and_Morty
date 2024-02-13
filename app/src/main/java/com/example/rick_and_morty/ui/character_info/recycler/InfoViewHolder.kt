package com.example.rick_and_morty.ui.character_info.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.InfoViewBinding
import com.example.rick_and_morty.databinding.ItemViewBinding
import com.example.rick_and_morty.domain.model.character.Character

class InfoViewHolder(private val binding: InfoViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(pair: Pair<String, String>) = with(binding) {
        tvFirst.text = pair.first
        tvSecond.text = pair.second
    }

}