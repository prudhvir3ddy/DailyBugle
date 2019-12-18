package com.prudhvir3ddy.dailybugle.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.R
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

        rootView.bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.menu_item_search -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                    findNavController().navigate(action)
                }
                R.id.menu_item_save -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToSaveFragment()
                    findNavController().navigate(action)
                }
            }
            false
        }

        return rootView
    }


}
