package com.graduation.mawruth.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.graduation.mawruth.databinding.MuseumItemBinding

class MuseumRecyclerAdapter(var list: MutableList<TestMuseumObject>) :
    RecyclerView.Adapter<MuseumRecyclerAdapter.ViewHolder>() {

    class ViewHolder(var itemBinding: MuseumItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            MuseumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.rate.text = list[position].museumRate
        holder.itemBinding.chip1.text = list[position].chip1
        holder.itemBinding.chip2.text = list[position].chip2
        holder.itemBinding.chip3.text = list[position].chip3
        holder.itemBinding.museumName.text = list[position].museumName
        holder.itemBinding.museumLocation.text = list[position].museumLocation
        holder.itemBinding.musImage.background =
            ContextCompat.getDrawable(holder.itemBinding.root.context, list[position].image)

    }
}