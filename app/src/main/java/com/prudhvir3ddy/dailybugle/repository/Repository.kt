package com.prudhvir3ddy.dailybugle.repository

import android.content.Context
import android.util.Log
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel

class Repository constructor(
    private val database: NewsDao,
    private val context: Context,
    private val newsApiService: NewsApiService
) {
    suspend fun getTopHeadLines(country: String){
        val getTopHeadLinesDeferred =
            newsApiService.getTopHeadlinesAsync(
                country,
                BuildConfig.apiNews
            )
        try {
            val resultList = getTopHeadLinesDeferred.body()
            database.clear(country)
            val listResult = resultList!!.articles.map {
                it.asDatabaseModel(country)
            }
            database.insert(listResult)
        } catch (e: Exception) {
            Log.d("topNewsResult", e.message.toString())
        }
    }

     suspend fun getData(country: String): List<UIDatabaseArticles>{
        return database.getAllArticles(country)
    }

    fun getConnection():Boolean {
        return Connection.hasNetwork(context)
    }



}

