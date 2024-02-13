package com.example.rick_and_morty.di

import android.content.Context
import com.example.rick_and_morty.data.NetworkClient
import com.example.rick_and_morty.data.network.RetrofitNetworkClient
import com.example.rick_and_morty.data.network.RickAndMortyAPI
import com.example.rick_and_morty.data.repository.CharacterRepositoryImpl
import com.example.rick_and_morty.domain.api.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRickAndMortyService(): RickAndMortyAPI =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyAPI::class.java)


    @Provides
    @Singleton
    fun provideNetworkClient(
        @ApplicationContext context: Context,
        rickAndMortyService: RickAndMortyAPI
    ): NetworkClient =
        RetrofitNetworkClient(context, rickAndMortyService)


    @Provides
    @Singleton
    fun provideCharacterRepository(
        networkClient: NetworkClient,
        @ApplicationContext context: Context
    ): CharacterRepository =
        CharacterRepositoryImpl(networkClient, context)
}