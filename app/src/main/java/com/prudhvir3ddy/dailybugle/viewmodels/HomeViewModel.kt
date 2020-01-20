package com.prudhvir3ddy.dailybugle.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles
import com.prudhvir3ddy.dailybugle.network.data.Articles
import com.prudhvir3ddy.dailybugle.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: Repository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    init {
        getData()
    }

    private var _topNews= MutableLiveData<List<DatabaseArticles>>()

    val topNews: LiveData<List<DatabaseArticles>>
    get() = _topNews

    private val _status = MutableLiveData<Boolean>()

    val status: LiveData<Boolean>
        get() = _status

    private fun getDataFromRepo(isCache: Boolean) {
        viewModelScope.launch {
            val country = sharedPreferences.getString("country", "in")
            if(!isCache)  repo.getTopHeadLines(country!!)
            _topNews.value = repo.getData(country!!)
            if(_topNews.value!!.isEmpty()) _status.value = true
        }
    }


    fun resetStatus() {
        _status.value = false
    }

    fun getData() {
        if(repo.getConnection()){
            getDataFromRepo(false)
        }
        else{
             getDataFromRepo(true)
        }
    }
}