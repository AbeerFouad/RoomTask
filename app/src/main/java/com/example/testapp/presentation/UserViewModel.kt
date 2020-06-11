package com.example.testapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.contracts.IUserRepository
import com.example.testapp.domain.entities.*
import com.example.testapp.presentation.ShowUsers.UsersState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(private val repo: IUserRepository) : ViewModel() {

    private val _addedUserLiveData = MutableLiveData<Long?>()
    val addedUserLiveData: LiveData<Long?> = _addedUserLiveData

    private val _usersLiveData = MutableLiveData<UsersState>()
    val usersLiveData: LiveData<UsersState> = _usersLiveData


    fun addUser(name: String, age: Int?, job: String?, gender: String) {
        viewModelScope.launch {
            _addedUserLiveData.value = repo.addUser(
                User(
                    name = Name(name),
                    age = if (age != null) Age(age) else null,
                    jobTitle = if (job != null) JobTitle(job) else null,
                    gender = Gender.valueOf(gender)
                )
            )
        }
    }

    fun getUsers() {
        _usersLiveData.value = UsersState.Loading
        viewModelScope.launch {
            repo.getAllUsers().collect { users ->
                if (users.isEmpty()) _usersLiveData.value = UsersState.Empty
                else _usersLiveData.value = UsersState.Success(users)
            }
        }
    }

    fun reset(){
        _addedUserLiveData.value = null
    }
}