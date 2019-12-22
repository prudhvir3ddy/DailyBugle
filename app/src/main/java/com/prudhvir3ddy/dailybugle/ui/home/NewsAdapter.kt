package com.prudhvir3ddy.dailybugle.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.network.data.Articles
import kotlinx.android.synthetic.main.item_top_news.view.*

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var list: List<Articles>

    fun setData(it: List<Articles>) {
        list = it
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_top_news,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if (::list.isInitialized)
            return list.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageView: ImageView = holder.itemView.main_image
        val url: String? = list.get(position).urlToImage

        bindImage(imageView, url)

        holder.itemView.title.text = list.get(position).title
    }
}