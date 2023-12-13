package com.gerardo.userlist.domain.repositories

import com.gerardo.userlist.domain.entities.pagination.Page
import com.gerardo.userlist.domain.entities.user.BasicUserInfo
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.domain.result.DomainResult

interface UserRepository {
    suspend fun getUsers(): DomainResult<List<UserInfo>>
    suspend fun getUsersPage(pageNumber: Int): DomainResult<Page<BasicUserInfo>>
    suspend fun saveUserInfo(userInfo: UserInfo): DomainResult<UserInfo>
    suspend fun getUserInfo(email: String): DomainResult<UserInfo>
}
