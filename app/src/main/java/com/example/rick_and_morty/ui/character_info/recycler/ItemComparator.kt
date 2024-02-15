package com.example.rick_and_morty.ui.character_info.recycler

import androidx.recyclerview.widget.DiffUtil

class ItemComparator: DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean {
         return oldItem == newItem
    }
}