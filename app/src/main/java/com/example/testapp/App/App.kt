package com.example.testapp.App

import android.app.Application
import com.example.testapp.di.databaseModule
import com.example.testapp.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    userModule,
                    databaseModule

                )
            )
        }
    }
}