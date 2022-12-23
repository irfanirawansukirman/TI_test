package com.irfanirawansukirman.telkomindonesiatest.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irfanirawansukirman.telkomindonesiatest.databinding.ItemNewsBinding

/**
 * Created by Irfan Irawan Sukirman on 23/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class NewsAdapter(
    private val news: List<NewsUI>,
    private val newsItemListener: NewsItemListener
) : RecyclerView.Adapter<NewsAdapter.NewsItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemHolder {
        return NewsItemHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsItemHolder, position: Int) {
        holder.bindItem(news[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class NewsItemHolder(private val viewBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItem(newsUI: NewsUI) {
            viewBinding.tvTitle.text = newsUI.title
            viewBinding.tvPublishWithAuthor.text = "${newsUI.time}, by ${newsUI.by}"
            viewBinding.viewListener.setOnClickListener { newsItemListener.onSelectedNews(newsUI) }
        }
    }
}