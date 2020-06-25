package com.prudhvir3ddy.dailybugle.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles
import com.prudhvir3ddy.dailybugle.databinding.ItemNewsBinding

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
class ArticlesViewHolder(private val itemNewsBinding: ItemNewsBinding) :
    RecyclerView.ViewHolder(itemNewsBinding.root) {

  fun bind(item: UIDatabaseArticles) {
    itemNewsBinding.model = item
    itemNewsBinding.executePendingBindings()
  }

}

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
    val binding: ItemNewsBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_news,
        parent,
        false
    )
    return ArticlesViewHolder(binding)
  }

  override fun onBindViewHolder(
      holder: ArticlesViewHolder,
      position: Int
  ) {
    val model = getItem(position)
    holder.bind(model)
  }
}