package com.prudhvir3ddy.dailybugle.network

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient{

    companion object {

        private val BASE_URL = "http://newsapi.org/v2/"

        private lateinit var retrofit:Retrofit

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getRetrofitInstance(context: Context): Retrofit {

            if (!::retrofit.isInitialized)
                retrofit = createRetrofit(context)

            return retrofit
        }

        private fun createRetrofit(context: Context): Retrofit {

            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(context.cacheDir, cacheSize)


            val REWRITE_RESPONSE_INTERCEPTOR = Interceptor{
                val response:Response = it.proceed(it.request())
                val cacheControl:String? = response.header("Cache-Control")
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build();
                } else {
                    response;
                }
            }

            val REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = Interceptor {
                var request: Request = it.request()
                if(!Connection.hasNetwork(context)){
                    request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
                }
                it.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                .build()

            retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()

            return retrofit
        }
    }
}