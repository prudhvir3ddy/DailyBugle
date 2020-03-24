package com.prudhvir3ddy.dailybugle.di

import android.content.Context
import com.prudhvir3ddy.dailybugle.MainActivity
import com.prudhvir3ddy.dailybugle.ui.home.HomeFragment
import com.prudhvir3ddy.dailybugle.ui.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * To generate dagger app component
 * we define all starting points of graph here
 */
@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

  // Factory to create instances of the AppComponent
  @Component.Factory
  interface Factory {
    // With @BindsInstance, the Context passed in will be available in the graph
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(homeFragment: HomeFragment)
  fun inject(mainActivity: MainActivity)
  fun inject(searchFragment: SearchFragment)
}