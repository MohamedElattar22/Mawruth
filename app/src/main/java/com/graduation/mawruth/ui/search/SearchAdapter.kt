package com.graduation.mawruth.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.museums.MuseumItem
import com.graduation.mawruth.databinding.SearchItemLayoutBinding

class SearchAdapter(var museumList: List<MuseumItem?>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    class MyViewHolder(val viewBinding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SearchItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return museumList.size
    }

    fun bindMuseumList(list: List<MuseumItem?>) {
        this.museumList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val museumItem = museumList[position]
        holder.viewBinding.museumName.text = museumItem?.name
        Glide.with(holder.itemView)
            .load(museumItem?.images?.get(0)?.imagePath.toString())
            .into(holder.viewBinding.museumImg)
        holder.itemView.setOnClickListener {
            onMuseumClickListener?.onClick(museumItem!!, position)
        }
    }

    var onMuseumClickListener: OnMuseumClickListener? = null

    fun interface OnMuseumClickListener {
        fun onClick(museumDto: MuseumItem, position: Int)
    }
}