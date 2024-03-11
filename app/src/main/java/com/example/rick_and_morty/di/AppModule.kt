package com.example.rick_and_morty.di

import android.content.Context
import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.ui.character_list.view_model.RecyclerFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideRecyclerFactory(
        searchCharactersUseCase: SearchCharactersUseCase,
        databaseInteractor: DatabaseInteractor,
    ): RecyclerFactory {
        return RecyclerFactory(searchCharactersUseCase, databaseInteractor)
    }
}