package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api

import com.gerardo.userlist.domain.entities.pagination.Page
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomUserResponseApi(
    @Json(name = "info") val paginationInfo: InfoApi,
    @Json(name = "results") val users: List<UserInfoApi>
)

fun RandomUserResponseApi.toUserList() = users.map { it.toUserInfo() }

fun RandomUserResponseApi.toUserPage(): Page<UserInfo> = Page(
    previousPage = paginationInfo.currentPage - 1,
    nextPage = paginationInfo.currentPage + 1,
    items = this.toUserList()
)
