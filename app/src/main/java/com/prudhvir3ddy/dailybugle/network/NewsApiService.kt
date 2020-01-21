package com.prudhvir3ddy.dailybugle.network


import com.prudhvir3ddy.dailybugle.network.data.ResponseNews
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getEveryThingAsync(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<ResponseNews>

    @GET("top-headlines")
    suspend fun getTopHeadlinesAsync(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ResponseNews>

}
