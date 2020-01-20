package com.prudhvir3ddy.dailybugle.repository

import android.content.Context
import android.util.Log
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApi
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val database: NewsDao,
    private val context: Context
) {
    suspend fun getTopHeadLines(country: String){
        val getTopHeadLinesDeferred =
            NewsApi().newsService.getTopHeadlinesAsync(
                country,
                BuildConfig.apiNews
            )
        try {
            val resultList = getTopHeadLinesDeferred.await()
            database.clear(country)
            val listResult = resultList.articles.map {
                it.asDatabaseModel(country)
            }
            database.insert(listResult)
        } catch (e: Exception) {
            Log.d("topNewsResult", e.message.toString())
        }
    }

     suspend fun getData(country: String): List<DatabaseArticles>{
        return database.getAllArticles(country)
    }

    fun getConnection():Boolean {
        return Connection.hasNetwork(context)
    }



}

