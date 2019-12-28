package com.prudhvir3ddy.dailybugle.ui.home

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.prudhvir3ddy.dailybugle.R
import com.prudhvir3ddy.dailybugle.bindImage
import com.prudhvir3ddy.dailybugle.network.data.Source
import kotlinx.android.synthetic.main.item_news.view.*

class SourceAdapter(private val context: Context): RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private lateinit var list: List<Source>

    fun setData(it: List<Source>) {
        list = it
        notifyDataSetChanged()
    }

    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_news,parent,false)
        )
    }

    override fun getItemCount(): Int {
        if(::list.isInitialized)
            return list.size
        return 0
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {

        val imageView: ImageView = holder.itemView.main_image

        val iconUrl = "https://besticon-demo.herokuapp.com/icon?url=%s&size=64..64..120"
        val url = String.format(iconUrl, Uri.parse(list.get(position).url).authority)

        bindImage(imageView, url)

        holder.itemView.title.text = list.get(position).name
    }
}