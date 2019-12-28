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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _foundNews = MutableLiveData<News>()

    val foundNews: LiveData<News>
        get() = _foundNews

    fun searchNews(query: String) {
        coroutineScope.launch {

            val getNewsDeferred = NewsApi(getApplication()).newsService.getEveryThing(query, BuildConfig.apiNews)
            try {
                val resultList = getNewsDeferred.await()
                _foundNews.value = resultList
            } catch (e: Exception) {
                Log.d("newsResult", e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}