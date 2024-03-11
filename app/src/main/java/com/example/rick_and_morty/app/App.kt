package com.example.rick_and_morty.app

import android.app.Application
import com.example.rick_and_morty.di.AppComponent
import com.example.rick_and_morty.di.AppModule
import com.example.rick_and_morty.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    }

}