package com.gerardo.userlist.di

import com.gerardo.userlist.framework.ui.loaders.ImageLoader
import com.gerardo.userlist.framework.ui.loaders.coil.ImageLoaderCoil
import org.koin.dsl.module

val frameworkModule = module {
    factory<ImageLoader> { ImageLoaderCoil() }
}
