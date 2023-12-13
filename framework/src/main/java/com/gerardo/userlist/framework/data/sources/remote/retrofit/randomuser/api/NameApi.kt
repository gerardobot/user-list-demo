package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameApi(
    @Json(name = "first") val first: String,
    @Json(name = "last") val last: String
)

fun NameApi.toFullName() = "$first $last"
