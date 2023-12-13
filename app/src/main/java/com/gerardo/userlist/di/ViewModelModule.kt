package com.gerardo.userlist.di

import com.gerardo.userlist.ui.screens.user.list.UserListViewModel
import com.gerardo.userlist.ui.screens.user.profile.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserListViewModel(get()) }
    viewModel { UserInfoViewModel(get(), get()) }
}
