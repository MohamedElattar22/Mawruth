package com.graduation.mawruth.ui.home.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.mawruth.databinding.ViewPagerItemBinding

class HomeViewPager(private var list: List<Int>) :
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
        holder.itemBinding.bg.setImageResource(list[position])

    }
}