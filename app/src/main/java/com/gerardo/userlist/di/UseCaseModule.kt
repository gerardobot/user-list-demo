package com.gerardo.userlist.di

import com.gerardo.userlist.domain.usecases.user.GetUserInfoUseCase
import com.gerardo.userlist.domain.usecases.user.GetUsersPageUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUsersPageUseCase(get()) }
    factory { GetUserInfoUseCase(get()) }
}
