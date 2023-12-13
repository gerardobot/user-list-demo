package com.gerardo.userlist.framework.data.sources.remote.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServiceInstanceCreator(
    private val context: Context
) {
    inline fun <reified T> create(
        baseUrl: String,
        interceptors: List<Interceptor> = listOf()
    ): T = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        client(getClient(interceptors))
    }.build().create(T::class.java)

    fun getClient(interceptors: List<Interceptor>): OkHttpClient = OkHttpClient.Builder().apply {
        interceptors.forEach { addInterceptor(it) }
        addInterceptor(ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true).build())
        readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        writeTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
    }.build()

    companion object {
        private const val TIMEOUT_IN_SECONDS = 60
    }
}
