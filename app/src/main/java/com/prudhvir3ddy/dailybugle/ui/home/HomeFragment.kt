package com.prudhvir3ddy.dailybugle.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.network.Connection
import com.prudhvir3ddy.dailybugle.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)

        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)


        val newsAdapter = NewsAdapter()
        val sourceAdapter = SourceAdapter()

        getData(viewModel)

        rootView.apply {

            recycler_view_top_news.adapter = newsAdapter
            recycler_view_sources.adapter = sourceAdapter

            swipe_refresh.isRefreshing = true

            swipe_refresh.setOnRefreshListener {
                getData(viewModel)
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

        viewModel.sources.observe(this, Observer {
            sourceAdapter.submitList(it.sources)
        })

        viewModel.topNews.observe(this, Observer {
            newsAdapter.submitList(it.articles)
            rootView.swipe_refresh.isRefreshing = false
        })

        viewModel.status.observe(this, Observer {
            if(it){
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToNoInternetFragment()
                )
                viewModel.resetStatus()
            }
        })


        return rootView
    }

    fun getData(viewModel:HomeViewModel){
        viewModel.apply {
            getTopHeadLines()
            getSources()
        }
    }

}
