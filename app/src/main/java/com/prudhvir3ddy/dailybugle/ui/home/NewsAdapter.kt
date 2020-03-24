package com.prudhvir3ddy.dailybugle.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import kotlinx.android.synthetic.main.item_news.view.main_image
import kotlinx.android.synthetic.main.item_news.view.title

/**
 * DiffUtil is an optimisation for recyclerview
 * read more here:
 * https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil
 */
object NewsDiffCallback : DiffUtil.ItemCallback<UIDatabaseArticles>() {
  override fun areItemsTheSame(
    oldItem: UIDatabaseArticles,
    newItem: UIDatabaseArticles
  ): Boolean {
    return oldItem.title == newItem.title
  }

  override fun areContentsTheSame(
    oldItem: UIDatabaseArticles,
    newItem: UIDatabaseArticles
  ): Boolean {
    return oldItem == newItem
  }

}

/**
 * Adapter for main screen recyclerview where top headlines shows up
 * this adapter is responsible to sit between the list we passed
 * and UI that shows up
 */
class NewsAdapter : ListAdapter<UIDatabaseArticles, NewsAdapter.NewsViewHolder>(
  NewsDiffCallback
) {

  /**
   * view holder for the items
   */
  class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): NewsViewHolder {
    return NewsViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.item_news,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(
    holder: NewsViewHolder,
    position: Int
  ) {

    val imageView: ImageView = holder.itemView.main_image
    val url: String? = getItem(position).urlToImage

    bindImage(imageView, url)

    holder.itemView.title.text = getItem(position).title
  }
}