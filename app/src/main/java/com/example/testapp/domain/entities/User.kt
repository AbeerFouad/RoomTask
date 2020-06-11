package com.example.testapp.domain.entities


inline class Name(val value: String?)
inline class Age(val value: Int?)
inline class JobTitle(val value: String?)

enum class Gender {
    Male,
    Female
}

data class User(
    val name: Name,
    val age: Age?,
    val jobTitle: JobTitle?,
    val gender: Gender = Gender.Male
)