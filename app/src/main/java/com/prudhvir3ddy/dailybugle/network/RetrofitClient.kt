package com.prudhvir3ddy.dailybugle.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient{

    companion object getRetrofit {

        private val BASE_URL = "https://newsapi.org/v2/"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        private val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        fun getRetrofitInstance(): Retrofit {
            return retrofit
        }
    }
}