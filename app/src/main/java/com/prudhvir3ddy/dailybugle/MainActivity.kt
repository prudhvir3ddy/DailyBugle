package com.prudhvir3ddy.dailybugle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.dailybugle.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelJob = Job()

        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

        coroutineScope.launch {
            val getNewsDeferred = NewsApi.newsService.getEveryThing(BuildConfig.apiNews)
            try {

                val resultList = getNewsDeferred.await()
                Log.d("newsResult", "${resultList.totalResults}")

            } catch (e: Exception) {
                Log.d("newsResult", e.message!!)
            }
        }
    }
}
