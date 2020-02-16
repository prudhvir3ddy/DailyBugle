package com.prudhvir3ddy.dailybugle.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prudhvir3ddy.dailybugle.ui.home.HomeViewModel
import com.prudhvir3ddy.dailybugle.ui.search.SearchViewModel
import com.prudhvir3ddy.dailybugle.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelScope(HomeViewModel::class)
  abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(SearchViewModel::class)
  abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}