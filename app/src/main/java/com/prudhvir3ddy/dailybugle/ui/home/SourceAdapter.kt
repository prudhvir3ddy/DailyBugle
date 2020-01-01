package com.prudhvir3ddy.dailybugle.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.network.data.Source
import kotlinx.android.synthetic.main.item_news.view.*

object SourceDiffCallback : DiffUtil.ItemCallback<Source>() {
    override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem == newItem
    }

}

class SourceAdapter : ListAdapter<Source, SourceAdapter.SourceViewHolder>(
    SourceDiffCallback
) {

    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {

        val imageView: ImageView = holder.itemView.main_image

        val iconUrl = "https://besticon-demo.herokuapp.com/icon?url=%s&size=64..64..120"
        val url = String.format(iconUrl, Uri.parse(getItem(position).url).authority)

        bindImage(imageView, url)

        holder.itemView.title.text = getItem(position).name
    }
}