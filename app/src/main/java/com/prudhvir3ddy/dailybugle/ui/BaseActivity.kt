package com.prudhvir3ddy.dailybugle.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prudhvir3ddy.dailybugle.utils.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : Fragment() {
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        bindContentView()
        super.onCreate(savedInstanceState)
    }

    private fun bindContentView() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

}