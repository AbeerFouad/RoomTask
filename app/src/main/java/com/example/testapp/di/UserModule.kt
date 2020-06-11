package com.example.testapp.di

import com.example.testapp.App.AppDataBase
import com.example.testapp.data.local.dao.UserDao
import com.example.testapp.data.repository.UserRepository
import com.example.testapp.domain.contracts.IUserRepository
import com.example.testapp.presentation.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    single { createLocalUserDao(get()) }

    single<IUserRepository> { UserRepository(get()) }

    viewModel { UserViewModel(get()) }
}

fun createLocalUserDao(appDataBase: AppDataBase): UserDao {
    return appDataBase.userDao()
}