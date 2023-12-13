package com.gerardo.userlist.data.repositories

import com.gerardo.userlist.data.sources.local.LocalDataKey
import com.gerardo.userlist.data.sources.local.LocalDataSource
import com.gerardo.userlist.data.sources.remote.RandomUserRemoteDataSource
import com.gerardo.userlist.domain.entities.pagination.Page
import com.gerardo.userlist.domain.entities.user.BasicUserInfo
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.domain.entities.user.toBasicUserInfo
import com.gerardo.userlist.domain.repositories.UserRepository
import com.gerardo.userlist.domain.result.DomainResult
import com.gerardo.userlist.domain.result.toDomainResult

class UserRepositoryImpl(
    private val randomUserRemoteDataSource: RandomUserRemoteDataSource,
    private val localDataSource: LocalDataSource
) : UserRepository {
    override suspend fun getUsers(): DomainResult<List<UserInfo>> =
        randomUserRemoteDataSource.getUsers()

    override suspend fun getUsersPage(pageNumber: Int): DomainResult<Page<BasicUserInfo>> {
        val localUsers = localDataSource.getAll(LocalDataKey.UserInfoKey::class.java).getOrNull() ?: emptyList()
        val localEmails = localUsers.map { it.email }.toSet()

        return randomUserRemoteDataSource.getUsersPage(pageNumber).onSuccess { page ->
            val uniqueUsers = page.items
                .filter { it.email !in localEmails }
                .distinctBy { it.email }
            uniqueUsers.forEach { saveUserInfo(it) }
            Page(
                previousPage = page.previousPage,
                nextPage = page.nextPage,
                items = uniqueUsers
            )
        }.map { page -> page.map { it.toBasicUserInfo() } }
    }

    override suspend fun saveUserInfo(userInfo: UserInfo): DomainResult<UserInfo> =
        localDataSource.saveData(LocalDataKey.UserInfoKey(userInfo.email), userInfo)
            .toDomainResult()

    override suspend fun getUserInfo(email: String) =
        localDataSource.getData(LocalDataKey.UserInfoKey(email)).toDomainResult()
}
