package com.graduation.mawruth.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.graduation.mawruth.databinding.OnboardingPagesLayoutBinding

class ViewPagerAdapter(var imagesList : List<Int> , var titles : List<String> , var content : List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.pagerViewHolder>() {

     var onClick: onNextBtnClick ?= null
    class pagerViewHolder(val viewBinding : OnboardingPagesLayoutBinding)
        :RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pagerViewHolder {
        val view = OnboardingPagesLayoutBinding.
        inflate(LayoutInflater.from(parent.context) , parent , false)

        return pagerViewHolder(view)
    }


    override fun getItemCount(): Int = titles.size

    override fun onBindViewHolder(holder: pagerViewHolder, position: Int) {
        holder.viewBinding.contentTV.text = content[position]
        holder.viewBinding.featureTV.text = titles[position]
        holder.viewBinding.desImage.setImageResource(imagesList[position])
        holder.viewBinding.nextBtn.isVisible = false

        if(position == titles.size-1){
            holder.viewBinding.nextBtn.isVisible = true
        }
        holder.viewBinding.nextBtn.setOnClickListener {
            onClick?.onBtnClick()
        }



    }


}