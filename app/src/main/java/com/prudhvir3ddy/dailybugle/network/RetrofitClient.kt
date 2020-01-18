package com.prudhvir3ddy.dailybugle.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {

    companion object {

        private val BASE_URL = "https://newsapi.org/v2/"

        private lateinit var retrofit: Retrofit

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getRetrofitInstance(): Retrofit {

            if (!::retrofit.isInitialized)
                retrofit = createRetrofit()

            return retrofit
        }

        private fun createRetrofit(): Retrofit {

            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()

            return retrofit
        }
    }
}