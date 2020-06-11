package com.example.testapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int?,
    val jobTitle: String?,
    val gender: String
)