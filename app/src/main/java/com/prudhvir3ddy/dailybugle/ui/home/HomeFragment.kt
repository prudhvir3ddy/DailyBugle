package com.prudhvir3ddy.dailybugle.ui.home


import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.ui.BaseFragment
import com.prudhvir3ddy.dailybugle.ui.NewsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>(),
    SharedPreferences.OnSharedPreferenceChangeListener {


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        viewModel.status.observe(this, Observer {
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

    override fun onResume() {
        super.onResume()
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)

    }

    override fun onDestroy() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "country") {
            viewModel.getData()
        }
    }


}
