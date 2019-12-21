package com.prudhvir3ddy.dailybugle.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.dailybugle.BuildConfig
import com.prudhvir3ddy.dailybugle.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel(){

    val viewModelJob = Job()

    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getEverything(){
        coroutineScope.launch {
            val getNewsDeferred = NewsApi.newsService.getEveryThing("bitcoin",BuildConfig.apiNews)
            try {

                val resultList = getNewsDeferred.await()
                Log.d("newsResult", "${resultList.totalResults}")

            } catch (e: Exception) {
                Log.d("newsResult", e.message!!)
            }
        }
    }
}