package com.prudhvir3ddy.dailybugle.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prudhvir3ddy.dailybugle.ui.detail.DetailViewModel
import com.prudhvir3ddy.dailybugle.ui.home.HomeViewModel
import com.prudhvir3ddy.dailybugle.ui.saved.SavedViewModel
import com.prudhvir3ddy.dailybugle.ui.search.SearchViewModel
import com.prudhvir3ddy.dailybugle.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * to bind viewmodels to dagger graph
 */
@Module
abstract class ViewModelModule {

  /**
   * factory tells dagger how to provide
   */
  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  /**
   * binding homeviewmodel into dagger
   */
  @Binds
  @IntoMap
  @ViewModelScope(HomeViewModel::class)
  abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  /**
   *  binding searchingviewmodel to dagger
   */
  @Binds
  @IntoMap
  @ViewModelScope(SearchViewModel::class)
  abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(SavedViewModel::class)
  abstract fun bindSavedViewModel(savedViewModel: SavedViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(DetailViewModel::class)
  abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

}