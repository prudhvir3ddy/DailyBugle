package com.prudhvir3ddy.dailybugle.network

import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.network.data.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

  @Headers("X-Api-Key:" + BuildConfig.apiNews)
  @GET("everything")
  suspend fun getEveryThingAsync(
    @Query("q") q: String
  ): Response<News>

  @GET("top-headlines")
  @Headers("X-Api-Key:" + BuildConfig.apiNews)
  suspend fun getTopHeadlinesAsync(
    @Query("country") country: String
  ): Response<News>

}
