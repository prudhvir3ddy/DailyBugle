package com.prudhvir3ddy.dailybugle.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.network.NewsApi
import com.prudhvir3ddy.dailybugle.network.data.News
import com.prudhvir3ddy.dailybugle.network.data.Sources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val _sources = MutableLiveData<Sources>()


    val sources: LiveData<Sources>
        get() = _sources

    private val _topNews = MutableLiveData<News>()

    val topNews: LiveData<News>
        get() = _topNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getSources(country: String){
        coroutineScope.launch {

            val getSourcesDeferred = NewsApi(getApplication()).newsService.getSources(country,BuildConfig.apiNews)
            try {
                val resultList = getSourcesDeferred.await()
                _sources.value = resultList
            } catch (e: Exception) {
                Log.d("newsResult", e.message.toString())
                if (e.message.equals("HTTP 504 Unsatisfiable Request (only-if-cached)"))
                    _status.value = true
            }
        }
    }

    fun getTopHeadLines(country: String) {
        coroutineScope.launch {

            val getTopHeadLinesDeferred =
                NewsApi(getApplication()).newsService.getTopHeadlines(country, BuildConfig.apiNews)
            try {
                val resultList = getTopHeadLinesDeferred.await()
                Log.d("topNewsResult", resultList.status)
                _topNews.value = resultList

            } catch (e: Exception) {
                Log.d("topNewsResult", e.message.toString())
            }
        }
    }

    fun resetStatus(){
        _status.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}