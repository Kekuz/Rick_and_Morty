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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val networkClient: NetworkClient,
    private val context: Context
) : CharacterRepository {

    override fun search(page: Int): Flow<Resource<CharacterResponse>> = flow {
        val response = networkClient.doRequest(CharacterRequest(page))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error(context.getString(R.string.internet_problems)))

            }

            200 -> {
                Log.d("Response", (response as CharacterResponseDto).results.toString())
                with(response as CharacterResponseDto) {
                    val data = CharacterMapper.map(response)
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error(context.getString(R.string.server_error)))
            }
        }
    }
}