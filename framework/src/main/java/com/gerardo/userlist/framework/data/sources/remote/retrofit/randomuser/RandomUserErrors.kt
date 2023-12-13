package com.gerardo.userlist.framework.data.sources.remote.retrofit.randomuser

import com.gerardo.userlist.domain.result.error.DomainError
import retrofit2.HttpException
import java.io.IOException

fun Exception.toDomainError() = when (this) {
    is HttpException -> DomainError.NetworkError(this)
    is IOException -> DomainError.IoError(this)
    else -> DomainError.Unknown(this)
}
