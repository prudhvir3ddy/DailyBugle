package com.prudhvir3ddy.dailybugle.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.view.bottom_navigation
import kotlinx.android.synthetic.main.fragment_search.view.recycler_view_search_news
import kotlinx.android.synthetic.main.fragment_search.view.search_input

/**
 * Search screen UI
 */
class SearchFragment : BaseFragment<SearchViewModel>() {

  override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

  override fun onCreate(savedInstanceState: Bundle?) {
    (context?.applicationContext as MyApplication).appComponent.inject(this)

    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    val rootView = inflater.inflate(R.layout.fragment_search, container, false)

    val searchNewsAdapter = SearchNewsAdapter()

    viewModel.foundNews.observe(viewLifecycleOwner, Observer {
      searchNewsAdapter.submitList(it)
    })


    viewModel.status.observe(viewLifecycleOwner, Observer {
      if (it) {
        findNavController().navigate(
          SearchFragmentDirections.actionSearchFragmentToNoInternetFragment()
        )
        viewModel.resetStatus()
      }
    })

    rootView.apply {
      search_input.setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          viewModel.searchNews(search_input.text.toString())
          search_input.text.clear()
        }
        false
      }
      recycler_view_search_news.adapter = searchNewsAdapter


      bottom_navigation.setOnNavigationItemSelectedListener {
        when (it.itemId) {

          R.id.menu_item_home -> {
            val action =
              SearchFragmentDirections.actionSearchFragmentToHomeFragment()
            findNavController().navigate(action)
          }
          R.id.menu_item_save -> {
            val action =
              SearchFragmentDirections.actionSearchFragmentToSaveFragment()
            findNavController().navigate(action)
          }
        }
        false
      }
    }
    return rootView
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (context.applicationContext as MyApplication).appComponent.inject(this);

  }

}
