package com.prudhvir3ddy.dailybugle.network

//import android.app.Application
//import android.content.Context
//import androidx.lifecycle.ViewModelProviders
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.prudhvir3ddy.dailybugle.database.NewsDatabase
//import com.prudhvir3ddy.dailybugle.ui.home.HomeViewModel
//import com.prudhvir3ddy.dailybugle.viewmodels.HomeViewModelFactory
//
//class FetchDataWorker (context: Context, workerParams: WorkerParameters):
//        Worker(context, workerParams){
//    override fun doWork(): Result {
//
//        val dataSource = NewsDatabase.getInstance(context = applicationContext).newsDatabaseDao
//
//        val viewModel = HomeViewModel(database = dataSource, application = Application())
//
//
//
//        return Result.success()
//    }
//
//}