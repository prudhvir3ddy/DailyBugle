package com.prudhvir3ddy.dailybugle.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.prudhvir3ddy.dailybugle.MyApplication
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.databinding.FragmentDetailBinding
import com.prudhvir3ddy.dailybugle.ui.BaseFragment

class DetailFragment : BaseFragment<DetailViewModel>() {

  val args: DetailFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding: FragmentDetailBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

    val newsItem = args.newsItem
    binding.item = newsItem
    return binding.root
  }

  override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (context.applicationContext as MyApplication).appComponent.inject(this)
  }
}