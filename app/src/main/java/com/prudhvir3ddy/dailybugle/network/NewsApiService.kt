package com.prudhvir3ddy.dailybugle.network

import com.prudhvir3ddy.dailybugle.network.data.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getEveryThingAsync(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<News>

    @GET("top-headlines")
    suspend fun getTopHeadlinesAsync(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<News>

}
