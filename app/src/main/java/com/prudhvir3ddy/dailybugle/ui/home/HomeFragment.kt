package com.prudhvir3ddy.dailybugle.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.recycler_view_top_news
import kotlinx.android.synthetic.main.fragment_home.swipe_refresh
import kotlinx.android.synthetic.main.fragment_home.view.bottom_navigation
import kotlinx.android.synthetic.main.fragment_home.view.recycler_view_top_news
import kotlinx.android.synthetic.main.fragment_home.view.swipe_refresh

/**
 * top headlines screen UI
 */
class HomeFragment : BaseFragment<HomeViewModel>() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)

    initRootView(rootView)
    addObservers()
    return rootView
  }

  private fun addObservers() {
    viewModel.topNews.observe(viewLifecycleOwner, Observer {
      val adapter = recycler_view_top_news.adapter as NewsAdapter
      adapter.apply {
        submitList(it)
      }
      swipe_refresh.isRefreshing = false
    })

    viewModel.status.observe(viewLifecycleOwner, Observer {
      if (it) {
        findNavController().navigate(
          HomeFragmentDirections.actionHomeFragmentToNoInternetFragment()
        )
        viewModel.resetStatus()
      }
    })
  }

  fun initRootView(rootView: View) {
    val newsAdapter = NewsAdapter()

    rootView.apply {

      recycler_view_top_news.adapter = newsAdapter

      swipe_refresh.isRefreshing = true

      swipe_refresh.setOnRefreshListener {
        viewModel.getData()
      }

      bottom_navigation.setOnNavigationItemSelectedListener {
        when (it.itemId) {

          R.id.menu_item_search -> {
            val action =
              HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
          }
          R.id.menu_item_save -> {
            val action =
              HomeFragmentDirections.actionHomeFragmentToSaveFragment()
            findNavController().navigate(action)
          }
        }
        false
      }

    }

  }

  override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (context.applicationContext as MyApplication).appComponent.inject(this);
  }
}
