package com.prudhvir3ddy.dailybugle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prudhvir3ddy.dailybugle.R
import kotlinx.android.synthetic.main.fragment_no_internet.view.*

/**
 * A simple [Fragment] subclass.
 */
class NoInternetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_no_internet, container, false)

        rootView.retry_button.setOnClickListener {
            findNavController().navigate(
                NoInternetFragmentDirections.actionNoInternetFragmentToHomeFragment()
            )
        }
        return rootView
    }

}
