package com.prudhvir3ddy.dailybugle.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val newsApiService: NewsApiService
) : ViewModel() {

    private val _foundNews = MutableLiveData<List<DatabaseArticles>>()

    val foundNews: LiveData<List<DatabaseArticles>>
        get() = _foundNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    fun searchNews(query: String) {
        viewModelScope.launch {

            val getNewsDeferred =
                newsApiService.getEveryThingAsync(query, BuildConfig.apiNews)

            val resultList = getNewsDeferred.body()
            _foundNews.value = resultList?.articles?.map {
                it.asDatabaseModel("in")

            }
        }
    }

    fun resetStatus() {
        _status.value = false
    }

}