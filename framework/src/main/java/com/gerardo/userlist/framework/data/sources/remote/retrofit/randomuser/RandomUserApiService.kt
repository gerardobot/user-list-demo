package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser

import com.gerardo.userlist.framework.data.sources.remote.retrofit.*
import com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api.RandomUserResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiService {
    @GET(Endpoints.RandomUser.default)
    suspend fun getUsers(): RandomUserResponseApi

    @GET(Endpoints.RandomUser.default)
    suspend fun getUserPage(
        @Query(Queries.RandomUser.userPage) pageNumber: Int
    ): RandomUserResponseApi
}

fun createRandomUserApiServiceInstance(
    serviceInstanceCreator: ServiceInstanceCreator
): RandomUserApiService = serviceInstanceCreator.create(BaseUrls.randomUser)
