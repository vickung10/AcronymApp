package com.example.acronymapp.repository.remote

import com.example.acronymapp.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val factory by lazy { Moshi.Builder().build().let { MoshiConverterFactory.create(it) } }
    private val okHttpClient by lazy {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)
        }
        return@lazy clientBuilder.build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.nactem.ac.uk")
            .addConverterFactory(factory)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val acronymService: AcronymService by lazy { retrofit.create(AcronymService::class.java) }
}