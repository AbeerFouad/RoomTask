package com.example.testapp.data.local.entities

import com.example.testapp.domain.entities.*

/*Mapper to Map between data layer entities and domain layer entities*/
fun User.toLocalUser(): LocalUser {
    return LocalUser(
        name = name.value!!,
        age = age?.value,
        jobTitle = jobTitle?.value,
        gender = gender.name
    )
}

fun LocalUser.toUser(): User {
    return User(
        name = Name(name),
        age = age?.let { Age(age) },
        jobTitle = jobTitle?.let { JobTitle(jobTitle) },
        gender = Gender.valueOf(gender)
    )
}