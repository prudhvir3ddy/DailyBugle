package com.prudhvir3ddy.dailybugle.network

import kotlinx.coroutines.Deferred

interface NewsApiService{

}


object NewsApi {
    val newsService: NewsApiService by lazy {
        RetrofitClient.getRetrofitInstance().create(NewsApiService::class.java)
    }
}