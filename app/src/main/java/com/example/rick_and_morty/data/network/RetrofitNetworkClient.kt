package com.example.rick_and_morty.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.rick_and_morty.data.NetworkClient
import com.example.rick_and_morty.data.network.dto.CharacterRequest
import com.example.rick_and_morty.data.network.dto.Response
import io.reactivex.rxjava3.core.Single
import java.lang.Exception

class RetrofitNetworkClient(
    private val context: Context,
    private val rickAndMortyService: RickAndMortyAPI,
) : NetworkClient {
    override fun doRequest(dto: Any): Single<Response> {
        if (!isConnected()) {
            return Single.just(Response().apply { resultCode = -1 })
        }
        if (dto !is CharacterRequest) {
            return Single.just(Response().apply { resultCode = 400 })
        }
        return try {
            rickAndMortyService.searchCharacters(dto.page).map {
                it.apply { resultCode = 200 }
            }
        } catch (e: Exception) {
            Single.just(Response().apply { resultCode = 500 })
        }
    }


    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

}