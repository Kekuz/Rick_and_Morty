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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CharacterRepositoryImpl(
    private val networkClient: NetworkClient,
    private val context: Context
) : CharacterRepository {

    override fun search(page: Int): Single<Resource<CharacterResponse>> {
        return networkClient.doRequest(CharacterRequest(page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                when (it.resultCode) {
                    -1 -> {
                        Resource.Error(context.getString(R.string.internet_problems))
                    }

                    200 -> {
                        Log.d("Response", (it as CharacterResponseDto).results.toString())
                        with(it as CharacterResponseDto) {
                            val data = CharacterMapper.map(it)
                            Resource.Success(data)
                        }
                    }

                    else -> {
                        Resource.Error(context.getString(R.string.server_error))
                    }
                }
            }

    }
}