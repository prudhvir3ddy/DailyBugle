package com.prudhvir3ddy.dailybugle.network


import com.prudhvir3ddy.dailybugle.network.data.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * this is an interface where we define all our network  operations
 */
interface NewsApiService {

    /**
     * get all the articles based on the given query
     *  this is used in search screen. this needs internet
     */
    @GET("everything")
    suspend fun getEveryThingAsync(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<ResponseNews>

    /**
     * get top heeadlines of a country from the api
     * using internet this is implemented in main screen
     */
    @GET("top-headlines")
    suspend fun getTopHeadlinesAsync(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ResponseNews>

}
