package com.gerardo.userlist.domain.usecases.user

import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.domain.repositories.UserRepository
import com.gerardo.userlist.domain.result.DomainResult
import com.gerardo.userlist.domain.usecases.BaseUseCase

class GetUserInfoUseCase(
    private val userRepository: UserRepository
) : BaseUseCase<GetUserInfoUseCase.Params, UserInfo>() {
    override suspend fun call(params: Params): DomainResult<UserInfo> =
        userRepository.getUserInfo(params.email)

    data class Params(val email: String)
}
