package com.prudhvir3ddy.dailybugle.network


import com.prudhvir3ddy.dailybugle.network.data.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * this is an interface where we define all our network  operations
 */
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
