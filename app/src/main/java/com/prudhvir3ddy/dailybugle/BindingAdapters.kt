package com.prudhvir3ddy.dailybugle

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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
