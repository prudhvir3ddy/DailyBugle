package com.prudhvir3ddy.dailybugle.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.databinding.FragmentSearchBinding
import com.prudhvir3ddy.dailybugle.ui.BaseFragment

/**
 * Search screen UI
 */
class SearchFragment : BaseFragment<SearchViewModel>() {

  override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    val binding: FragmentSearchBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

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

    binding.apply {
      searchInput.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          viewModel.searchNews(searchInput.text.toString())
          searchInput.text.clear()
        }
        false
      }
      recyclerViewSearchNews.adapter = searchNewsAdapter


      bottomNavigation.setOnNavigationItemSelectedListener {
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
    return binding.root
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (context.applicationContext as MyApplication).appComponent.inject(this)
  }
}
