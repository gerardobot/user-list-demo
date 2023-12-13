package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser

import com.gerardo.userlist.data.sources.remote.RandomUserRemoteDataSource
import com.gerardo.userlist.domain.entities.pagination.Page
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.domain.result.DomainResult
import com.gerardo.userlist.domain.result.success
import com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api.toUserList
import com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.api.toUserPage

class RandomUserRemoteDataSourceRetrofit(
    private val randomUserApiService: RandomUserApiService
) : RandomUserRemoteDataSource {
    override suspend fun getUsers(): DomainResult<List<UserInfo>> =
        requestCatching {
            randomUserApiService.getUsers().toUserList()
        }

    override suspend fun getUsersPage(
        pageNumber: Int
    ): DomainResult<Page<UserInfo>> = requestCatching {
        randomUserApiService.getUserPage(pageNumber).toUserPage()
    }

    private inline fun <T> requestCatching(
        block: () -> T
    ): DomainResult<T> = try {
        success(block.invoke())
    } catch (requestException: Exception) {
        error(requestException.toDomainError())
    }
}
