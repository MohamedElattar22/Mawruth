package com.graduation.mawruth.ui.islamicmuseum.fragments.home.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.stories.StoryData
import com.graduation.mawruth.databinding.InnerStoryItemBinding

class InnerStoriesAdapter(var list: List<StoryData?>) :
    RecyclerView.Adapter<InnerStoriesAdapter.ViewHolder>() {
    class ViewHolder(var itemBinding: InnerStoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            InnerStoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun bindStoriesList(list: List<StoryData?>) {
        this.list = list
        notifyDataSetChanged()
    }

    var onStoryClickListener: OnStoryClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = list.get(position)
        holder.itemBinding.storyname.text = story?.name.toString()
        Glide.with(holder.itemView)
            .load(story?.image.toString())
            .into(holder.itemBinding.storyimage)
        onStoryClickListener.let {
            holder.itemBinding.root.setOnClickListener {
                onStoryClickListener?.onClick(list[position]!!, position)
            }
        }


    }

    fun interface OnStoryClickListener {
        fun onClick(storyData: StoryData, position: Int)
    }

}
