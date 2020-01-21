package com.prudhvir3ddy.dailybugle.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import kotlinx.coroutines.launch

class SearchViewModel (
    private val newsApiService: NewsApiService
) : ViewModel() {

    private val _foundNews = MutableLiveData<List<UIDatabaseArticles>>()

    val foundNews: LiveData<List<UIDatabaseArticles>>
        get() = _foundNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    fun searchNews(query: String) {
        viewModelScope.launch {

            val getNewsDeferred =
                newsApiService.getEveryThingAsync(query, BuildConfig.apiNews)
            try {
                val resultList = getNewsDeferred.body()
                _foundNews.value = resultList!!.articles.map {
                    it.asDatabaseModel("in")
                }
            } catch (e: Exception) {
                if (e.message.equals("HTTP 504 Unsatisfiable Request (only-if-cached)"))
                    _status.value = true
            }
        }
    }

    fun resetStatus() {
        _status.value = false
    }

}