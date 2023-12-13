package com.gerardo.userlist.data.sources.local

interface LocalDataSource {
    suspend fun <T : Any> saveData(key: LocalDataKey<T>, value: T): Result<T>
    suspend fun <T : Any> getData(key: LocalDataKey<T>): Result<T>
}
