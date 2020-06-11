package com.example.testapp.presentation.ShowUsers

import com.example.testapp.domain.entities.User

sealed class UsersState{
    object Loading : UsersState()
    object Empty : UsersState()
data class Success(val users: List<User>) : UsersState()}