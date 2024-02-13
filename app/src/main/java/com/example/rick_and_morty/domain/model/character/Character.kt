package com.example.rick_and_morty.domain.model.character

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    //origin
    //location
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)
