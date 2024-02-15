package com.example.rick_and_morty.ui.character_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.ItemViewBinding
import com.example.rick_and_morty.domain.model.character.Character

class ItemAdapter(
    private val onClick: (Character) -> Unit,
    private val onEndingList: () -> Unit,
) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private val characters = mutableListOf<Character>()

    fun addCharacters(characters: List<Character>) =
        this.characters.addAll(characters)

    fun clearCharacters() =
        this.characters.clear()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(characters[position], onClick)


        if (position == characters.size - 1) {
            onEndingList.invoke()
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }
}