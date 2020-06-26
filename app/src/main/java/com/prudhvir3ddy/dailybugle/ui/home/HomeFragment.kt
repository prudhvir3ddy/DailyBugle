package com.prudhvir3ddy.dailybugle.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.databinding.FragmentHomeBinding
import com.prudhvir3ddy.dailybugle.ui.BaseFragment
import com.prudhvir3ddy.dailybugle.ui.CustomClickListener
import kotlinx.android.synthetic.main.fragment_home.recycler_view_top_news
import kotlinx.android.synthetic.main.fragment_home.swipe_refresh

/**
 * top headlines screen UI
 */
class HomeFragment : BaseFragment<HomeViewModel>(), CustomClickListener {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val binding: FragmentHomeBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

    initRootView(binding)
    addObservers()
    return binding.root
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

  private fun initRootView(rootView: FragmentHomeBinding) {
    val newsAdapter = NewsAdapter(this)

    rootView.apply {

      recyclerViewTopNews.adapter = newsAdapter

      swipeRefresh.isRefreshing = true

      swipeRefresh.setOnRefreshListener {
        viewModel.getData()
      }

      bottomNavigation.setOnNavigationItemSelectedListener {
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
    (context.applicationContext as MyApplication).appComponent.inject(this)
  }

  override fun cardClicked(f: UIDatabaseArticles?) {
    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(f!!)
    findNavController().navigate(action)
  }
}
