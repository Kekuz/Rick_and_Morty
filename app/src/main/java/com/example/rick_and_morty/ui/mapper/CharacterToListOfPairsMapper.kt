package com.example.rick_and_morty.ui.mapper

import com.example.rick_and_morty.domain.model.character.Character

object CharacterToListOfPairsMapper {
    fun map(character: Character): List<Pair<String, String>> =
        deleteEmptyPair(
            mutableListOf(
                "Status" to character.status,
                "Species" to character.species,
                "Type" to character.type,
                "Gender" to character.gender,
                "Created" to character.created,
            ))


    private fun deleteEmptyPair(list: List<Pair<String, String>>): List<Pair<String, String>> {
        val result = mutableListOf<Pair<String, String>>()
        for (x in list) {
            if (x.second.isNotEmpty()) {
                result.add(x)
            }
        }
        return result
    }


}