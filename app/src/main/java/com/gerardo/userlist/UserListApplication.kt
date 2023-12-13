package com.gerardo.userlist

import android.app.Application
import com.gerardo.userlist.di.frameworkModule
import com.gerardo.userlist.di.localDataModule
import com.gerardo.userlist.di.remoteModule
import com.gerardo.userlist.di.repositoryModule
import com.gerardo.userlist.di.useCaseModule
import com.gerardo.userlist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserListApplication)
            modules(
                frameworkModule,
                localDataModule,
                remoteModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
