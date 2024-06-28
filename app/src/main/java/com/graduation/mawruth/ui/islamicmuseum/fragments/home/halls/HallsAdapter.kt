package com.graduation.mawruth.ui.islamicmuseum.fragments.home.halls

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.halls.HallItem
import com.graduation.mawruth.databinding.HallsItemBinding

class HallsAdapter(var list: List<HallItem?>) : RecyclerView.Adapter<HallsAdapter.ViewHolder>() {
    class ViewHolder(var itemBinding: HallsItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            HallsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun bindHallsList(list: List<HallItem?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hall = list.get(position)
        holder.itemBinding.hallName.text = hall?.name.toString()
        holder.itemBinding.hallNum.text = hall?.description.toString()
        Glide.with(holder.itemView)
            .load(hall?.imagePath.toString())
            .into(holder.itemBinding.hallImage)

    }

}