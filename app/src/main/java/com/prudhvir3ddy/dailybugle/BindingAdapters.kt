package com.prudhvir3ddy.dailybugle

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * This is a binding adapter
 * we can use this in xml image views
 * it will fetch the image in the provided url
 * using Glide library
 */
@BindingAdapter("urlToImage")
fun bindImage(
  imgView: ImageView,
  imgUrl: String?
) {
  Glide.with(imgView.context)
    .load(imgUrl)
    .centerCrop()
    .placeholder(R.drawable.loading)
    .into(imgView)
}
