package com.example.rick_and_morty.di

import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.DatabaseRepository
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.domain.impl.DatabaseInteractorImpl
import com.example.rick_and_morty.domain.impl.SearchCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideSearchCharactersUseCase(characterRepository: CharacterRepository): SearchCharactersUseCase =
        SearchCharactersUseCaseImpl(characterRepository)

    @Singleton
    @Provides
    fun provideDatabaseInteractor(databaseRepository: DatabaseRepository): DatabaseInteractor =
        DatabaseInteractorImpl(databaseRepository)
}