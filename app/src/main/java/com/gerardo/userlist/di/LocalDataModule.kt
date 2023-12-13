package com.gerardo.userlist.di

import com.gerardo.userlist.data.sources.local.LocalDataSource
import com.gerardo.userlist.framework.data.sources.local.hashmap.HashMapDataSource
import org.koin.dsl.module

val localDataModule = module {
    single<LocalDataSource> { HashMapDataSource() }
}
