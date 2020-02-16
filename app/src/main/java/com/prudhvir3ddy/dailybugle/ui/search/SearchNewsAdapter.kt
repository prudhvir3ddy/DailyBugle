package com.prudhvir3ddy.dailybugle.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.prudhvir3ddy.dailybugle.R.layout
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.network.data.Articles
import com.prudhvir3ddy.dailybugle.ui.ArticlesViewHolder
import kotlinx.android.synthetic.main.item_news.view.main_image
import kotlinx.android.synthetic.main.item_news.view.title

object SearchNewsDiffCallback : DiffUtil.ItemCallback<Articles>() {
  override fun areItemsTheSame(
    oldItem: Articles,
    newItem: Articles
  ): Boolean {
    return oldItem.title == newItem.title
  }

  override fun areContentsTheSame(
    oldItem: Articles,
    newItem: Articles
  ): Boolean {
    return oldItem == newItem
  }

}

class SearchNewsAdapter : ListAdapter<Articles, ArticlesViewHolder>(
    SearchNewsDiffCallback
) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ArticlesViewHolder {
    return ArticlesViewHolder(
        LayoutInflater.from(parent.context).inflate(
            layout.item_news,
            parent,
            false
        )
    )
  }

  override fun onBindViewHolder(
    holder: ArticlesViewHolder,
    position: Int
  ) {

    val imageView: ImageView = holder.itemView.main_image
    val url: String? = getItem(position).urlToImage

    bindImage(imageView, url)

    holder.itemView.title.text = getItem(position).title
  }
}