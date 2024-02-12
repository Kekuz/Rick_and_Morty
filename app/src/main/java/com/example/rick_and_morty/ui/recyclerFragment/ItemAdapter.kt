package com.example.rick_and_morty.ui.recyclerFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.ItemViewBinding
import com.example.rick_and_morty.domain.model.Character

class ItemAdapter(
    private val launches: List<Character>,
    private val onClick: (Character) -> Unit,
    private val onEndingList: () -> Unit,
) :
    RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(launches[position], onClick)


        if (position == launches.size - 1) {
            onEndingList.invoke()
        }

    }

    override fun getItemCount(): Int {
        return launches.size
    }
}