package com.example.rick_and_morty.di

import android.content.Context
import com.example.rick_and_morty.domain.api.DatabaseInteractor
import com.example.rick_and_morty.domain.api.SearchCharactersUseCase
import com.example.rick_and_morty.ui.character_info.view_model.ItemViewModel
import com.example.rick_and_morty.ui.character_list.view_model.RecyclerFactory
import com.example.rick_and_morty.ui.character_list.view_model.RecyclerViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRecyclerFactory(
        searchCharactersUseCase: SearchCharactersUseCase,
        databaseInteractor: DatabaseInteractor,
    ): RecyclerFactory {
        return RecyclerFactory(searchCharactersUseCase, databaseInteractor)
    }

}