package com.prudhvir3ddy.dailybugle.repository

import android.content.Context
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.network.data.ResponseArticles
import javax.inject.Inject

class SearchNewsRepository @Inject constructor(
  private val context: Context,
  private val newsApiService: NewsApiService
) {

  suspend fun getSearchedNewsFromApi(query: String): List<ResponseArticles> {
    val response = newsApiService.getEveryThingAsync(query, BuildConfig.apiNews)
    if (response.isSuccessful) {
      return response.body()!!.articles
    } else {
      //TODO add error
      throw IllegalArgumentException()
    }
  }

  fun getConnection(): Boolean {
    return Connection.hasNetwork(context)
  }

}