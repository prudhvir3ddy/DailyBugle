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
import com.prudhvir3ddy.dailybugle.database.data.DatabaseArticles

import kotlinx.android.synthetic.main.item_news.view.*

object NewsDiffCallback : DiffUtil.ItemCallback<DatabaseArticles>() {
    override fun areItemsTheSame(oldItem: DatabaseArticles, newItem: DatabaseArticles): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DatabaseArticles, newItem: DatabaseArticles): Boolean {
        return oldItem == newItem
    }

}

class NewsAdapter : ListAdapter<DatabaseArticles, NewsAdapter.NewsViewHolder>(
    NewsDiffCallback
) {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val imageView: ImageView = holder.itemView.main_image
        val url: String? = getItem(position).urlToImage

        bindImage(imageView, url)

        holder.itemView.title.text = getItem(position).title
    }
}