package com.example.rick_and_morty.ui.character_info.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty.databinding.InfoViewBinding

class InfoAdapter : RecyclerView.Adapter<InfoViewHolder>() {

    private val pairs = mutableListOf<Pair<String, String>>()

    fun clearPairs() = pairs.clear()

    fun addPairs(pairs: List<Pair<String, String>>) =
        this.pairs.addAll(pairs)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InfoViewBinding.inflate(inflater, parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(pairs[position])
    }

    override fun getItemCount(): Int {
        return pairs.size
    }
}