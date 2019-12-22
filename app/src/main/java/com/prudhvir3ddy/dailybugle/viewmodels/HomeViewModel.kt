package com.prudhvir3ddy.dailybugle.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.network.NewsApi
import com.prudhvir3ddy.dailybugle.network.data.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel(){

    private val _allNews = MutableLiveData<News>()

    val allNews: LiveData<News>
        get() = _allNews

    private val _topNews = MutableLiveData<News>()

    val topNews: LiveData<News>
        get() = _topNews


    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getEverything(){
        coroutineScope.launch {

            val getNewsDeferred = NewsApi.newsService.getEveryThing("bitcoin",BuildConfig.apiNews)
            try {
                val resultList = getNewsDeferred.await()
                _allNews.value = resultList
            } catch (e: Exception) {
                Log.d("newsResult", e.message)
            }
        }
    }

    fun getTopHeadLines() {
        coroutineScope.launch {

            val getTopHeadLinesDeferred =
                NewsApi.newsService.getTopHeadlines("IN", BuildConfig.apiNews)
            try {
                val resultList = getTopHeadLinesDeferred.await()
                _topNews.value = resultList

            } catch (e: java.lang.Exception) {
                Log.d("topNewsResult", e.message)
            }
        }
    }
}