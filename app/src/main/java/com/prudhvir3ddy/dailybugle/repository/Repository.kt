package com.prudhvir3ddy.dailybugle.repository

import android.content.Context
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import javax.inject.Inject

/**
 * repository class is  supposed to produce data to our viewmodels
 * repository is responsibe to provide data whether from database or network
 */
class Repository @Inject constructor(
  private val database: NewsDao,
  private val context: Context,
  private val newsApiService: NewsApiService
) {
  suspend fun getTopHeadLines(country: String) {
    val getTopHeadLinesDeferred =
      newsApiService.getTopHeadlinesAsync(
        country,
        BuildConfig.apiNews
      )
    val resultList = getTopHeadLinesDeferred.body()
    database.clear(country)
    val listResult = resultList!!.articles.map {
      it.asDatabaseModel(country)
    }
    database.insert(listResult)
  }

  /**
   * get articles data from database
   */
  suspend fun getData(country: String): List<UIDatabaseArticles> {
    return database.getAllArticles(country)
  }

  fun getConnection(): Boolean {
    return Connection.hasNetwork(context)
  }

}

