package com.gerardo.userlist.data.sources.local

import com.gerardo.userlist.domain.entities.user.UserInfo
import java.lang.reflect.ParameterizedType

/**
 * Associates a data type with a unique key, so that data can be stored and retrieved
 * maintaining type safety.
 *
 * @property dataType uses reflection to obtain the actual type of the data. Casting is unchecked,
 * but it is safe, as the class is of ParameterizedType and requires a generic to be specified at compile time.
 *
 * @property name can be overridden if needed to create dynamic keys that share a common name but differ in some
 * manner only known at runtime. An example of such a key could be:
 * <pre>
 * class DynamicKey(suffix: String) : LocalDataKey<T>() {
 * override val name = this.javaClass.simpleName + suffix
 * }
 * </pre>
 *
 * @throws LocalDataKeyException in the unlikely case that the dataType could not be
 * obtained because of a reflection error
 */

sealed class LocalDataKey<T>(val persistForever: Boolean = false) {
    data class UserInfoKey(val email: String) : LocalDataKey<UserInfo>() {
        override val name = super.name + "UserInfo"
    }

    open val name: String by lazy { this.javaClass.simpleName }
    open val dataType: Class<T> by lazy {
        try {
            @Suppress("UNCHECKED_CAST")
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<T>
        } catch (cause: Exception) {
            throw LocalDataKeyException(this, cause)
        }
    }

    override fun toString(): String = name
}

class LocalDataKeyException(private val key: LocalDataKey<*>, cause: Throwable) : Exception(cause) {
    override val message: String
        get() = "Could not obtain data type for key ${key.name}"
}
