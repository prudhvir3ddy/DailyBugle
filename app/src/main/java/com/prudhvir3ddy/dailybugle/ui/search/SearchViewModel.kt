package com.prudhvir3ddy.dailybugle.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.network.NewsApiService
import com.prudhvir3ddy.dailybugle.utils.asDatabaseModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for search fragment
 * viewmodels are independent of UI
 * so all logics goes in here and it is supposed to  provide data to UI
 */
class SearchViewModel @Inject constructor(
  private val newsApiService: NewsApiService
) : ViewModel() {

  private val _foundNews = MutableLiveData<List<UIDatabaseArticles>>()

  val foundNews: LiveData<List<UIDatabaseArticles>>
    get() = _foundNews

  private val _status = MutableLiveData<Boolean>()

  val status: LiveData<Boolean>
    get() = _status

  /**
   * this function provides data to the UI
   * by calling repository function which either gets data from network or database
   */
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

  /**
   * this is an event to show whether we are in no internet screen
   * once we get  internet and data is available we will go to main screen again
   */
  fun resetStatus() {
    _status.value = false
  }

}