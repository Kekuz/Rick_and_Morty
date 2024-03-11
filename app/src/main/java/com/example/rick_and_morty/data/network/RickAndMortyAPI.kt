package com.example.rick_and_morty.data.network


import com.example.rick_and_morty.data.network.dto.characterDto.CharacterResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyAPI {
    @GET("api/character")
    fun searchCharacters(@Query("page") page: Int): Single<CharacterResponseDto>
}