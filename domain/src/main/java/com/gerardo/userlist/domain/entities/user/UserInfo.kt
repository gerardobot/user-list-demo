package com.gerardo.userlist.domain.entities.user

import java.time.LocalDate

data class UserInfo(
    val profilePic: String,
    val fullName: String,
    val email: String,
    val gender: String,
    val registrationDate: LocalDate? = null,
    val phone: String? = null
)
