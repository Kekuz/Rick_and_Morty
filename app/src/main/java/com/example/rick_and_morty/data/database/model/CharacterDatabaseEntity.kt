package com.example.rick_and_morty.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("character_database")
data class CharacterDatabaseEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String,
)