package com.gerardo.userlist.domain.result

import com.gerardo.userlist.domain.result.error.DomainError
import kotlinx.coroutines.CancellationException

sealed class DomainResult<T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error<T>(val error: DomainError) : DomainResult<T>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    fun getOrNull(): T? = if (this is Success) data else null

    fun getOrDefault(default: T): T = if (this is Success) data else default

    fun errorOrNull(): DomainError? = if (this is Error) error else null

    inline fun <R> fold(onSuccess: (value: T) -> R, onError: (error: DomainError) -> R): R =
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(error)
        }

    inline fun onSuccess(action: (value: T) -> Unit): DomainResult<T> {
        if (this is Success) action(data)
        return this
    }

    inline fun onError(action: (value: DomainError) -> Unit): DomainResult<T> {
        if (this is Error) action(error)
        return this
    }

    inline fun <R> map(transform: (data: T) -> R): DomainResult<R> = when (this) {
        is Success -> { Success(transform(data)) }
        is Error -> Error(error)
    }

    inline fun <R> mapCatching(
        transform: (data: T) -> R,
        catching: (Exception) -> DomainError
    ): DomainResult<R> = when (this) {
        is Success -> resultCatching({ transform(data) }) { catching(it) }
        is Error -> Error(error)
    }

    override fun toString(): String = when (this) {
        is Success -> "Success: $data"
        is Error -> "Error: $error"
    }
}

inline fun <T, R> T.resultCatching(
    block: T.() -> R,
    catching: (Exception) -> DomainError
): DomainResult<R> = try {
    success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (e: Exception) {
    error(catching(e))
}

fun success(): DomainResult<Unit> = DomainResult.Success(Unit)
fun <T> success(data: T): DomainResult<T> = DomainResult.Success(data)
fun <T> error(error: DomainError): DomainResult<T> = DomainResult.Error(error)

fun <T> Result<T>.toDomainResult(): DomainResult<T> = fold(
    onSuccess = { success(it) },
    onFailure = { if (it is CancellationException) throw it else error(it) }
)
