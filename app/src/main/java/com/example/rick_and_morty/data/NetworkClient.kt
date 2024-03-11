package com.example.rick_and_morty.data

import com.example.rick_and_morty.data.network.dto.Response
import io.reactivex.rxjava3.core.Single

interface NetworkClient {
    fun doRequest(dto: Any): Single<Response>
}