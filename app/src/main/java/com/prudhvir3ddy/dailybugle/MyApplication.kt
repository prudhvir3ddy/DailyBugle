package com.prudhvir3ddy.dailybugle

import android.app.Application
import com.prudhvir3ddy.dailybugle.di.AppComponent
import com.prudhvir3ddy.dailybugle.di.DaggerAppComponent

class MyApplication : Application() {

  val appComponent: AppComponent by lazy {

    DaggerAppComponent.factory()
        .create(applicationContext)
  }

}