package com.prudhvir3ddy.dailybugle.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.NewsApi
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val _foundNews = MutableLiveData<List<DatabaseArticles>>()

    val foundNews: LiveData<List<DatabaseArticles>>
        get() = _foundNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    fun searchNews(query: String) {
        viewModelScope.launch {

            val getNewsDeferred =
                NewsApi().newsService.getEveryThingAsync(query, BuildConfig.apiNews)
            try {
                val resultList = getNewsDeferred.await()
                Log.d("rsize", "${resultList.articles.size}")
                _foundNews.value = resultList.articles.map {
                    it.asDatabaseModel("in")
                }
            } catch (e: Exception) {
                Log.d("boo","${e.toString()}")
                if (e.message.equals("HTTP 504 Unsatisfiable Request (only-if-cached)"))
                    _status.value = true
            }
        }
    }

    fun resetStatus() {
        _status.value = false
    }

}