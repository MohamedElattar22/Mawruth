package com.graduation.mawruth.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.graduation.mawruth.databinding.ViewPagerItemBinding

class HomeViewPager(private var list: MutableList<TestViewPagerObject>) :
    RecyclerView.Adapter<HomeViewPager.PagerViewHolder>() {

    class PagerViewHolder(val itemBinding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemBinding =
            ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.itemBinding.title.text = list[position].title
        holder.itemBinding.description.text = list[position].description
        holder.itemBinding.bg.background =
            ContextCompat.getDrawable(holder.itemBinding.root.context, list[position].image)
    }
}