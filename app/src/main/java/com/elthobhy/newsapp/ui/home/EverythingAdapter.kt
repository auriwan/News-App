package com.elthobhy.newsapp.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.ItemEverythingBinding

class EverythingAdapter: RecyclerView.Adapter<EverythingAdapter.EverythingViewHolder>() {
    private var list = ArrayList<Article>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EverythingAdapter.EverythingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_everything, parent, false)
        return EverythingViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onClicked(data: Article)
    }

    inner class EverythingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(article: Article) {
            val binding = ItemEverythingBinding.bind(itemView)
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(imageEverything)
                tvTitle.text = article.title
                tvSource.text = article.source?.name
                tvDate.text = article.publishedAt
            }
            itemView.setOnClickListener {
                onItemClickCallback.onClicked(article)
            }
        }

    }

    override fun onBindViewHolder(holder: EverythingAdapter.EverythingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun setList(article: List<Article>){
        list.addAll(article)
        Log.e("debug", "setList: $list", )
        notifyDataSetChanged()
    }
}