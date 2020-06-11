package com.example.testapp.domain.contracts

import com.example.testapp.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun addUser(user: User): Long
    fun getAllUsers(): Flow<List<User>>
}