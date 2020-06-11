package com.example.testapp.App

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.data.local.dao.UserDao
import com.example.testapp.data.local.entities.LocalUser

@Database(
    entities = [LocalUser::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}