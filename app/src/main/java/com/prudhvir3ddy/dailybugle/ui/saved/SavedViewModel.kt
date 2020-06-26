package com.prudhvir3ddy.dailybugle.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import javax.inject.Inject

class SavedViewModel @Inject constructor() : ViewModel() {
  private val _savedNews = MutableLiveData<List<UIDatabaseArticles>>()

  val savedNews: LiveData<List<UIDatabaseArticles>>
    get() = _savedNews

}