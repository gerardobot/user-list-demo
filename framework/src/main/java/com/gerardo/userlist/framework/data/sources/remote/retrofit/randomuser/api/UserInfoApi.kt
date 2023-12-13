package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api

import com.gerardo.userlist.domain.entities.user.UserInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import toDateFromPattern

@JsonClass(generateAdapter = true)
data class UserInfoApi(
    @Json(name = "gender") val gender: String,
    @Json(name = "name") val name: NameApi,
    @Json(name = "email") val email: String,
    @Json(name = "registered") val registration: RegistrationApi,
    @Json(name = "phone") val phone: String?,
    @Json(name = "cell") val cell: String?,
    @Json(name = "picture") val picture: PictureApi
)

fun UserInfoApi.toUserInfo() = UserInfo(
    profilePic = picture.profilePic,
    fullName = name.toFullName(),
    gender = gender,
    email = email,
    registrationDate = registration.date.toDateFromPattern(),
    phone = cell ?: phone
)
