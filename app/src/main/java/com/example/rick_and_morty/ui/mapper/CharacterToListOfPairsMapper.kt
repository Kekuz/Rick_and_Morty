package com.example.rick_and_morty.ui.mapper

import com.example.rick_and_morty.domain.model.character.Character

object CharacterToListOfPairsMapper {
    fun map(character: Character): List<Pair<String, String>> =
        listOf(
            "Status" to character.status.minusInsteadEmpty(),
            "Species" to character.species.minusInsteadEmpty(),
            "Type" to character.type.minusInsteadEmpty(),
            "Gender" to character.gender.minusInsteadEmpty(),
            "Created" to character.created.minusInsteadEmpty(),
        )

    private fun String.minusInsteadEmpty() =
        this.ifEmpty { "-" }
}