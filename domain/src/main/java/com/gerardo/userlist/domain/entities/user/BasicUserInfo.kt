package com.gerardo.userlist.domain.entities.user

data class BasicUserInfo(
    val profilePic: String,
    val fullName: String,
    val email: String
)

fun UserInfo.toBasicUserInfo() = BasicUserInfo(
    profilePic = profilePic,
    fullName = fullName,
    email = email
)
