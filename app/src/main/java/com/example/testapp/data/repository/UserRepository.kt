package com.example.testapp.data.repository

import com.example.testapp.data.local.dao.UserDao
import com.example.testapp.data.local.entities.toLocalUser
import com.example.testapp.data.local.entities.toUser
import com.example.testapp.domain.contracts.IUserRepository
import com.example.testapp.domain.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) : IUserRepository {

    override suspend fun addUser(user: User): Long {
        return userDao.insertUser(user.toLocalUser())
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { localUserList ->
            localUserList.map { localUser ->
                localUser.toUser()
            }
        }
    }
}