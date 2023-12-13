package com.gerardo.userlist.domain.usecases.user

import com.gerardo.userlist.domain.entities.pagination.Page
import com.gerardo.userlist.domain.entities.user.BasicUserInfo
import com.gerardo.userlist.domain.repositories.UserRepository
import com.gerardo.userlist.domain.result.DomainResult
import com.gerardo.userlist.domain.usecases.BaseUseCase

class GetUsersPageUseCase(
    private val userRepository: UserRepository
) : BaseUseCase<GetUsersPageUseCase.Params, Page<BasicUserInfo>>() {
    override suspend fun call(params: Params): DomainResult<Page<BasicUserInfo>> =
        userRepository.getUsersPage(params.pageNumber)

    data class Params(val pageNumber: Int)
}
