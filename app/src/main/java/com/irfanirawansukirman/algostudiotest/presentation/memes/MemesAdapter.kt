package com.irfanirawansukirman.algostudiotest.presentation.memes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.irfanirawansukirman.algostudiotest.databinding.ItemMemeBinding
import com.irfanirawansukirman.algostudiotest.remote.data.response.Meme

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class MemesAdapter(private val onMemeSelected: (String) -> Unit) :
    RecyclerView.Adapter<MemesAdapter.ItemMemeHolder>() {

    private val memes = mutableListOf<Meme>()

    inner class ItemMemeHolder(private val viewBinding: ItemMemeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItem(meme: Meme, onMemeSelected: (String) -> Unit) {
            viewBinding.apply {
                ivPoster.load(meme.url)
                viewListener.setOnClickListener { onMemeSelected(meme.url.toString()) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMemeHolder {
        return ItemMemeHolder(
            ItemMemeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemMemeHolder, position: Int) {
        holder.bindItem(memes[holder.adapterPosition], onMemeSelected)
    }

    override fun getItemCount(): Int {
        return memes.size
    }

    fun addAllMemes(memes: List<Meme>) {
        this.memes.apply {
            clear()
            addAll(memes)
        }

        notifyDataSetChanged()
    }

    fun clearAllMemes() {
        memes.clear()
        notifyDataSetChanged()
    }
}