package com.gerardo.userlist.di

import com.gerardo.userlist.data.repositories.UserRepositoryImpl
import com.gerardo.userlist.domain.repositories.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
}
