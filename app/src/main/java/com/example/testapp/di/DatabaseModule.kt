package com.example.testapp.di

import android.content.Context
import androidx.room.Room
import com.example.testapp.App.AppDataBase
import org.koin.dsl.module

val databaseModule = module {
    single { createRoomDataBase(get()) }
}

const val DATABASE_NAME = "Test"

fun createRoomDataBase(context: Context): AppDataBase {
    return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}