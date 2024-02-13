package com.example.rick_and_morty.ui.character_info.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.InfoViewBinding
import com.example.rick_and_morty.databinding.ItemViewBinding
import com.example.rick_and_morty.domain.model.character.Character

class InfoAdapter(
    private val launches: List<Pair<String, String>>,
) :
    RecyclerView.Adapter<InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InfoViewBinding.inflate(inflater, parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount(): Int {
        return launches.size
    }
}