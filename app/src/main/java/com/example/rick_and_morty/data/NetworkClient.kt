package com.example.rick_and_morty.data

import com.example.rick_and_morty.data.network.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}