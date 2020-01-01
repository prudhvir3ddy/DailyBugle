package com.prudhvir3ddy.dailybugle.viewmodels

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.network.NewsApi
import com.prudhvir3ddy.dailybugle.network.data.News
import com.prudhvir3ddy.dailybugle.network.data.Sources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val _sources = MutableLiveData<Sources>()

    val sources: LiveData<Sources>
        get() = _sources

    private val _topNews = MutableLiveData<News>()

    val topNews: LiveData<News>
        get() = _topNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application.applicationContext)

    var country = sharedPreferences.getString("country", "")

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getSources(isCached: String? = null) {
        coroutineScope.launch {

            val getSourcesDeferred =
                NewsApi(getApplication()).newsService.getSources(
                    country!!,
                    BuildConfig.apiNews
                )
            try {
                val resultList = getSourcesDeferred.await()
                _sources.value = resultList
            } catch (e: Exception) {
                Log.d("newsResult", e.message.toString())
            }
        }
    }

    fun getTopHeadLines(isCached: String? = null) {
        coroutineScope.launch {

            val getTopHeadLinesDeferred =
                NewsApi(getApplication()).newsService.getTopHeadlines(
                    country!!,
                    BuildConfig.apiNews
                )
            try {
                val resultList = getTopHeadLinesDeferred.await()
                Log.d("topNewsResult", resultList.status)
                _topNews.value = resultList

            } catch (e: Exception) {
                Log.d("topNewsResult", e.message.toString())
            }
        }
    }

    fun resetStatus() {
        _status.value = false
    }

    fun getData(isCached: String? = null) {
        if (Connection.hasNetwork(getApplication())) {
            country = sharedPreferences.getString("country", "")
            getTopHeadLines(isCached)
            getSources(isCached)
        } else {
            _status.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "country") {
            getData()
        }
    }




}