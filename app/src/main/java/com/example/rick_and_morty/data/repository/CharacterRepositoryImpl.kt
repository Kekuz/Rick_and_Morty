package com.example.rick_and_morty.data.repository

import android.content.Context
import android.util.Log
import com.example.rick_and_morty.R
import com.example.rick_and_morty.data.NetworkClient
import com.example.rick_and_morty.data.mapper.CharacterMapper
import com.example.rick_and_morty.data.network.dto.CharacterRequest
import com.example.rick_and_morty.data.network.dto.characterDto.CharacterResponseDto
import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.model.Resource
import com.example.rick_and_morty.domain.model.character.CharacterResponse
import java.text.SimpleDateFormat
import java.util.Locale

class CharacterRepositoryImpl(
    private val networkClient: NetworkClient,
    private val context: Context
) : CharacterRepository {

    override fun search(page: Int): Resource<CharacterResponse> {
        val response = networkClient.doRequest(CharacterRequest(page))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error(context.getString(R.string.internet_problems))
            }

            200 -> {
                Log.d("Tracks", (response as CharacterResponseDto).results.toString())
                Resource.Success(CharacterMapper.map(response))
            }

            else -> {
                Resource.Error(context.getString(R.string.server_error))
            }
        }
    }
}