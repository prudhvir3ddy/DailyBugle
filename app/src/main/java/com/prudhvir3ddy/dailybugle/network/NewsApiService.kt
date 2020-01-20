package com.prudhvir3ddy.dailybugle.network


import com.prudhvir3ddy.dailybugle.network.data.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    fun getEveryThingAsync(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Deferred<News>

    @GET("top-headlines")
    fun getTopHeadlinesAsync(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Deferred<News>

}

class NewsApi {
    val newsService: NewsApiService by lazy {
        RetrofitClient.getRetrofitInstance().create(NewsApiService::class.java)
    }
}