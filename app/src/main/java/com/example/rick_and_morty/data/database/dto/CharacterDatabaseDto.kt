package com.example.rick_and_morty.data.database.dto

data class CharacterDatabaseDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String,
)