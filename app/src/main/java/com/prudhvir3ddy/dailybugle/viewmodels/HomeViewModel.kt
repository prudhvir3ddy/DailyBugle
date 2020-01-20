package com.prudhvir3ddy.dailybugle.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.preference.PreferenceManager
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.repository.Repository
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY_PROPERTY_NAME
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    init {
        refreshDataFromRepo()
    }


    val repo = Repository(application)


    private var _topNews= MutableLiveData<List<DatabaseArticles>>()

    val topNews: LiveData<List<DatabaseArticles>>
    get() = _topNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(getApplication())

    private fun refreshDataFromRepo() {
        viewModelScope.launch {
            val country = sharedPreferences.getString("country", "in")
            repo.getTopHeadLines(country!!)
            _topNews.value = repo.getData()
        }
    }


    fun resetStatus() {
        _status.value = false
    }

    fun getData() {
        if (Connection.hasNetwork(getApplication())) {
            refreshDataFromRepo()
        } else {
            _status.value = true
        }
    }


}