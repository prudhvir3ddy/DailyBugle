package com.prudhvir3ddy.dailybugle.repository

import android.content.Context
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.data.DatabaseTopHeadlines
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import javax.inject.Inject

class HeadLinesRepository @Inject constructor(
  private val database: NewsDao,
  private val context: Context,
  private val newsApiService: NewsApiService
) {
  suspend fun getTopHeadLinesFromApi(country: String) {
    val response =
      newsApiService.getTopHeadlinesAsync(country)

    if (response.isSuccessful) {
      val resultList = response.body()
      database.clearTopHeadlines(country)
      val listResult = resultList?.articles?.map {
        it.asDatabaseModel(country)
      }
      database.insertTopHeadlines(listResult!!)
    } else {
      //TODO handle error
    }
  }

  suspend fun getTopHeadlinesFromDatabase(country: String): List<DatabaseTopHeadlines> {
    return database.getTopHeadlines(country)
  }

  fun getConnection(): Boolean {
    return Connection.hasNetwork(context)
  }

}

