package com.prudhvir3ddy.dailybugle.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R.layout
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import kotlinx.android.synthetic.main.item_news.view.main_image
import kotlinx.android.synthetic.main.item_news.view.title

/**
 * DiffUtil is an optimisation for recyclerview
 * read more here:
 * https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil
 */
object SearchNewsDiffCallback : DiffUtil.ItemCallback<UIDatabaseArticles>() {
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
 * viewholder of items
 */
class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

/**
 * Searched news adapter
 * to show data in recyclerview with provided data
 */
class SearchNewsAdapter : ListAdapter<UIDatabaseArticles, ArticlesViewHolder>(
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