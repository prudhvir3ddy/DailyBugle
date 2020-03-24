package com.prudhvir3ddy.dailybugle

import android.app.Application
import com.prudhvir3ddy.dailybugle.di.AppComponent
import com.prudhvir3ddy.dailybugle.di.DaggerAppComponent

/**
 * This is the MainApplication class
 * it stays in scope from application starting to ending
 * right now i am only injecting dagger here.
 *
 * if you need any other things which need to stay through
 * entire application you can add it here
 */
class MyApplication : Application() {

  val appComponent: AppComponent by lazy {

    DaggerAppComponent.factory()
      .create(applicationContext)
  }

}