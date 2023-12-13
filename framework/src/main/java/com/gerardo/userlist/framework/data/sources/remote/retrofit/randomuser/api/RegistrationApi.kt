package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationApi(
    @Json(name = "date") val date: String
)
