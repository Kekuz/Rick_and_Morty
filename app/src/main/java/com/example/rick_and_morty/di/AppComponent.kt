package com.example.rick_and_morty.di

import com.example.rick_and_morty.ui.character_info.fragment.ItemFragment
import com.example.rick_and_morty.ui.character_list.fragment.RecyclerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(itemFragment: ItemFragment)

    fun inject(recyclerFragment: RecyclerFragment)
}