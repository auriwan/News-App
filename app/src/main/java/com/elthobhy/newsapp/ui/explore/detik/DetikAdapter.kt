package com.elthobhy.newsapp.ui.explore.detik

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ItemTopHeadlinesBinding
import com.elthobhy.newsapp.ui.home.HeadlineAdapter

class DetikAdapter:RecyclerView.Adapter<DetikAdapter.DetikViewHolder>() {
    private val list = ArrayList<Article>()
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetikAdapter.DetikViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines, parent, false)
        return DetikViewHolder(view)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }

    inner class DetikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTopHeadlinesBinding.bind(itemView)
        fun bind(detikEntity: Article) {
            binding.apply {
                Glide.with(itemView)
                    .load(detikEntity.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageTopHeadline)
                tvTitleTopHeadlines.text = detikEntity.title
                tvSourceName.text = detikEntity.source?.name
                tvDateNews.text = detikEntity.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(detikEntity)
            }
        }
    }

    override fun onBindViewHolder(holder: DetikAdapter.DetikViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(detikEntity: List<Article>) {
        list.addAll(detikEntity)
        Log.e("debug", "setList: $list",)
        notifyDataSetChanged()
    }
}