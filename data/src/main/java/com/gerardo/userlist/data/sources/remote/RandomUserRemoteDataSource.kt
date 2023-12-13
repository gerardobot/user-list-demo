package com.gerardo.userlist.data.sources.remote

import com.gerardo.userlist.domain.entities.pagination.Page
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.domain.result.DomainResult

interface RandomUserRemoteDataSource {
    suspend fun getUsers(): DomainResult<List<UserInfo>>
    suspend fun getUsersPage(pageNumber: Int): DomainResult<Page<UserInfo>>
}
