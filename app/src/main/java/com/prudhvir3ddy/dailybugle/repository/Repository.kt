package com.prudhvir3ddy.dailybugle.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.NewsDao
import com.prudhvir3ddy.dailybugle.database.NewsDatabase
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticleSource
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.NewsApi
import com.prudhvir3ddy.dailybugle.network.data.ArticleSource
import com.prudhvir3ddy.dailybugle.network.data.Articles
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel

class Repository(application: Application) {

    var database: NewsDao = NewsDatabase.getInstance(application).newsDatabaseDao



    suspend fun getTopHeadLines(country: String){
        val getTopHeadLinesDeferred =
            NewsApi().newsService.getTopHeadlines(
                country,
                BuildConfig.apiNews
            )
        try {
            val resultList = getTopHeadLinesDeferred.await()
            database.clear()
            val listResult = resultList.articles.map {
                it.asDatabaseModel(country)
            }


            database.insert(listResult)


        } catch (e: Exception) {

            Log.d("topNewsResult", e.message.toString())
        }
    }

     suspend fun getData(): List<DatabaseArticles>{
        return database.getAllArticles()
    }



}

