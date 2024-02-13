package com.example.rick_and_morty.di

import com.example.rick_and_morty.domain.api.CharacterRepository
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.domain.impl.SearchCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideSearchCharactersUseCase(characterRepository: CharacterRepository): SearchCharactersUseCase =
        SearchCharactersUseCaseImpl(characterRepository)

}