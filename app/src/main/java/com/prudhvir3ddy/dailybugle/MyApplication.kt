package com.prudhvir3ddy.dailybugle

import android.app.Application
import com.prudhvir3ddy.dailybugle.injection.networkModule
import com.prudhvir3ddy.dailybugle.injection.repositoryModule
import com.prudhvir3ddy.dailybugle.injection.sharedPreferencesModule
import com.prudhvir3ddy.dailybugle.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)

            androidLogger()

            modules(listOf(
                networkModule,
                sharedPreferencesModule,
                viewModelModule,
                repositoryModule
            ))
        }
    }


}