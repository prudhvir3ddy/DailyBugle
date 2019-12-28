package com.prudhvir3ddy.dailybugle.network

import android.content.Context
import com.prudhvir3ddy.dailybugle.network.data.News
import com.prudhvir3ddy.dailybugle.network.data.Sources
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService{

    @GET("everything")
    fun getEveryThing(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Deferred<News>

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Deferred<News>

    @GET("sources")
    fun getSources(
        @Query("apiKey") apiKey: String
    ): Deferred<Sources>

}

class NewsApi(context: Context) {
    val newsService: NewsApiService by lazy {
        RetrofitClient.getRetrofitInstance(context).create(NewsApiService::class.java)
    }
}