package com.prudhvir3ddy.dailybugle.repository

import android.content.Context
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.network.data.Articles
import javax.inject.Inject

class SearchNewsRepository @Inject constructor(
  private val context: Context,
  private val newsApiService: NewsApiService
) {

  suspend fun getSearchedNewsFromApi(query: String): List<Articles> {
    val response = newsApiService.getEveryThingAsync(query)
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