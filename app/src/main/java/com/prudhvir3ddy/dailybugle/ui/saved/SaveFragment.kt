package com.prudhvir3ddy.dailybugle.ui.saved

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.databinding.FragmentSaveBinding
import com.prudhvir3ddy.dailybugle.ui.BaseFragment

/**
 * saved articles screen UI
 * not implemented yet
 */
class SaveFragment : BaseFragment<SavedViewModel>() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val binding: FragmentSaveBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_save, container, false)

    //  val savedNewsAdapter = SavedNewsAdapter()

//    viewModel.savedNews.observe(viewLifecycleOwner, Observer {
//      //TODO savedNewsAdapter.submitList(it)
//    })

    binding.bottomNavigation.setOnNavigationItemSelectedListener {
      when (it.itemId) {

        R.id.menu_item_search -> {
          val action =
            SaveFragmentDirections.actionSaveFragmentToSearchFragment()
          findNavController().navigate(action)
        }
        R.id.menu_item_home -> {
          val action =
            SaveFragmentDirections.actionSaveFragmentToHomeFragment()
          findNavController().navigate(action)
        }
      }
      false
    }

    return binding.root
  }

  override fun getViewModelClass(): Class<SavedViewModel> = SavedViewModel::class.java

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (context.applicationContext as MyApplication).appComponent.inject(this)
  }
}
