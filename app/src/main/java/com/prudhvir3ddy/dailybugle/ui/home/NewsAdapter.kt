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
import com.prudhvir3ddy.dailybugle.network.data.Articles
import kotlinx.android.synthetic.main.item_news.view.*

object NewsDiffCallback : DiffUtil.ItemCallback<Articles>() {
    override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem == newItem
    }

}

class NewsAdapter : ListAdapter<Articles, NewsAdapter.NewsViewHolder>(
    NewsDiffCallback
) {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

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