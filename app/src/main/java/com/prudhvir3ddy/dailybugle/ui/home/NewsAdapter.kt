package com.prudhvir3ddy.dailybugle.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.databinding.ItemNewsBinding
import com.prudhvir3ddy.dailybugle.ui.CustomClickListener

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
class NewsAdapter(private val itemClickListener: CustomClickListener) :
  ListAdapter<UIDatabaseArticles, NewsAdapter.NewsViewHolder>(
    NewsDiffCallback
  ) {

  /**
   * view holder for the items
   */
  class NewsViewHolder(private val itemNewsBinding: ItemNewsBinding) : RecyclerView.ViewHolder(
    itemNewsBinding.root
  ) {
    fun bind(
      item: UIDatabaseArticles,
      itemClickListener: CustomClickListener
    ) {
      itemNewsBinding.model = item
      itemNewsBinding.executePendingBindings()
      itemNewsBinding.itemClickListener = itemClickListener
    }

  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): NewsViewHolder {

    val binding: ItemNewsBinding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.context),
      R.layout.item_news, parent, false
    )
    return NewsViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: NewsViewHolder,
    position: Int
  ) {
    val model = getItem(position)
    holder.bind(model, itemClickListener)
  }
}