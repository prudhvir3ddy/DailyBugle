package com.prudhvir3ddy.dailybugle.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.prudhvir3ddy.dailybugle.R.layout
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.database.data.DatabaseTopHeadlines
import com.prudhvir3ddy.dailybugle.ui.ArticlesViewHolder
import kotlinx.android.synthetic.main.item_news.view.main_image
import kotlinx.android.synthetic.main.item_news.view.title

object TopHeadlinesDiffCallback : DiffUtil.ItemCallback<DatabaseTopHeadlines>() {
  override fun areItemsTheSame(
    oldItem: DatabaseTopHeadlines,
    newItem: DatabaseTopHeadlines
  ): Boolean {
    return oldItem.title == newItem.title
  }

  override fun areContentsTheSame(
    oldItem: DatabaseTopHeadlines,
    newItem: DatabaseTopHeadlines
  ): Boolean {
    return oldItem == newItem
  }

}

class TopHeadlinesAdapter : ListAdapter<DatabaseTopHeadlines, ArticlesViewHolder>(
    TopHeadlinesDiffCallback
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