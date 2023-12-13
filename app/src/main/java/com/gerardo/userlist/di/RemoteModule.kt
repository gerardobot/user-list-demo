package com.gerardo.userlist.di

import com.gerardo.userlist.data.sources.remote.RandomUserRemoteDataSource
import com.gerardo.userlist.framework.data.sources.remote.retrofit.ServiceInstanceCreator
import com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.RandomUserRemoteDataSourceRetrofit
import com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser.createRandomUserApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteModule = module {
    single { ServiceInstanceCreator(androidContext()) }

    single { createRandomUserApiServiceInstance(get()) }
    single<RandomUserRemoteDataSource> { RandomUserRemoteDataSourceRetrofit(get()) }
}
