package com.example.rick_and_morty.di

import android.content.Context
import androidx.room.Room
import com.example.rick_and_morty.data.DatabaseClient
import com.example.rick_and_morty.data.NetworkClient
import com.example.rick_and_morty.data.database.CharacterDatabase
import com.example.rick_and_morty.data.database.RoomDatabaseClient
import com.example.rick_and_morty.data.network.RetrofitNetworkClient
import com.example.rick_and_morty.data.network.RickAndMortyAPI
import com.example.rick_and_morty.data.repository.CharacterRepositoryImpl
import com.example.rick_and_morty.data.repository.DatabaseRepositoryImpl
import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.api.DatabaseRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//@Singleton
class DataModule {

    @Provides
    fun provideRickAndMortyService(): RickAndMortyAPI =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyAPI::class.java)


    @Provides
    fun provideCharacterDatabase(context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "character-database"
        ).build()


    @Provides
    fun provideNetworkClient(
        context: Context,
        rickAndMortyService: RickAndMortyAPI
    ): NetworkClient =
        RetrofitNetworkClient(context, rickAndMortyService)

    @Provides
    fun provideDatabaseClient(
        characterDatabase: CharacterDatabase,
    ): DatabaseClient =
        RoomDatabaseClient(characterDatabase)


    @Provides
    fun provideCharacterRepository(
        networkClient: NetworkClient,
        context: Context
    ): CharacterRepository =
        CharacterRepositoryImpl(networkClient, context)

    @Provides
    fun provideDatabaseRepository(
        databaseClient: DatabaseClient,
    ): DatabaseRepository =
        DatabaseRepositoryImpl(databaseClient)

}