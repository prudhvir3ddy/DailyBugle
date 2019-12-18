package com.prudhvir3ddy.dailybugle.network

import com.prudhvir3ddy.dailybugle.network.data.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService{

    @GET("everything")
    fun getEveryThing(
        @Query("apiKey") apiKey: String
    ): Deferred<News>

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String
    ): Deferred<News>

    @GET("source")
    fun getFromSources(
        @Query("apiKey") apiKey: String
    ): Deferred<News>

}

object NewsApi {
    val newsService: NewsApiService by lazy {
        RetrofitClient.getRetrofitInstance().create(NewsApiService::class.java)
    }
}