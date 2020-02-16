package com.prudhvir3ddy.dailybugle.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudhvir3ddy.dailybugle.network.data.Articles
import com.prudhvir3ddy.dailybugle.repository.SearchNewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
  private val repo: SearchNewsRepository
) : ViewModel() {

  private val _foundNews = MutableLiveData<List<Articles>>()

  val foundNews: LiveData<List<Articles>>
    get() = _foundNews

  private val _status = MutableLiveData<Boolean>()

  val status: LiveData<Boolean>
    get() = _status

  fun searchNewsFromRepo(
    query: String
  ) {
    viewModelScope.launch {
      //TODO handle no internet case
      _foundNews.value = repo.getSearchedNewsFromApi(query)
      // _foundNews.value = repo.getSearchedNewsFromDatabase(query)
      if (_foundNews.value!!.isEmpty()) {
        _status.value = true
      }
    }
  }

  fun resetStatus() {
    _status.value = false
  }

}