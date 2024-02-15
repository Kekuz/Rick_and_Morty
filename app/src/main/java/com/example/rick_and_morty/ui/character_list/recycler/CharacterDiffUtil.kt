package com.example.rick_and_morty.ui.character_list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.rick_and_morty.domain.model.character.Character

class CharacterDiffUtil(
    private val oldList: List<Character>,
    private val newList: List<Character>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].status != newList[newItemPosition].status -> false
            oldList[oldItemPosition].species != newList[newItemPosition].species -> false
            oldList[oldItemPosition].type != newList[newItemPosition].type -> false
            oldList[oldItemPosition].gender != newList[newItemPosition].gender -> false
            oldList[oldItemPosition].image != newList[newItemPosition].image -> false
            oldList[oldItemPosition].created != newList[newItemPosition].created -> false
            else -> true
        }
    }
}