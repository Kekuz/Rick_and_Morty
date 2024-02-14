package com.example.rick_and_morty.data.network.dto.characterDto

data class CharacterDto(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    //origin
    //location
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
)