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
import com.prudhvir3ddy.dailybugle.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)

        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val newsAdapter = NewsAdapter(activity!!.applicationContext)
        val sourceAdapter = SourceAdapter(activity!!.applicationContext)

        viewModel.getTopHeadLines()
        viewModel.getSources()

        viewModel.sources.observe(this, Observer {
            sourceAdapter.setData(it.sources)
        })

        viewModel.topNews.observe(this, Observer {

            newsAdapter.setData(it.articles)
            Log.i("topNewsAll", "${it.articles.size ?: 0}")
        })

        rootView.apply {

            recycler_view_top_news.adapter = newsAdapter
            recycler_view_sources.adapter = sourceAdapter

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

        return rootView
    }

}
