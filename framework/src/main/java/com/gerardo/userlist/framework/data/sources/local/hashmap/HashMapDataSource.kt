package com.gerardo.userlist.framework.data.sources.local.hashmap

import com.gerardo.userlist.data.sources.local.LocalDataKey
import com.gerardo.userlist.data.sources.local.LocalDataSource

class HashMapDataSource : LocalDataSource {
    private val map = HashMap<LocalDataKey<*>, Any>()

    override suspend fun <T : Any> saveData(key: LocalDataKey<T>, value: T): Result<T> {
        map[key] = value
        return Result.success(value)
    }

    override suspend fun <T : Any> getData(key: LocalDataKey<T>): Result<T> = runCatching {
        @Suppress("UNCHECKED_CAST")
        return Result.success(map[key] as T)
    }

    override suspend fun <T : Any> getAll(keyClass: Class<out LocalDataKey<T>>): Result<List<T>> = runCatching {
        @Suppress("UNCHECKED_CAST")
        return Result.success(map.filterKeys { it.javaClass == keyClass }.values.toList() as List<T>)
    }
}
