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
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "character-database"
        ).build()


    @Provides
    @Singleton
    fun provideNetworkClient(
        @ApplicationContext context: Context,
        rickAndMortyService: RickAndMortyAPI
    ): NetworkClient =
        RetrofitNetworkClient(context, rickAndMortyService)

    @Provides
    @Singleton
    fun provideDatabaseClient(
        characterDatabase: CharacterDatabase,
    ): DatabaseClient =
        RoomDatabaseClient(characterDatabase)


    @Provides
    @Singleton
    fun provideCharacterRepository(
        networkClient: NetworkClient,
        @ApplicationContext context: Context
    ): CharacterRepository =
        CharacterRepositoryImpl(networkClient, context)

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        databaseClient: DatabaseClient,
    ): DatabaseRepository =
        DatabaseRepositoryImpl(databaseClient)

}