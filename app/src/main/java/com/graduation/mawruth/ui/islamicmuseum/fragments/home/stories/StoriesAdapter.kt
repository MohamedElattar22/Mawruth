package com.graduation.mawruth.ui.islamicmuseum.fragments.home.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.stories.StoryData
import com.graduation.mawruth.databinding.HomescreenStoriesItemBinding

class StoriesAdapter(var list: List<StoryData?>) :
    RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {
    class ViewHolder(var itemBinding: HomescreenStoriesItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            HomescreenStoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        holder.itemBinding.categoryName.text = story?.name.toString()
        Glide.with(holder.itemView)
            .load(story?.image.toString())
            .into(holder.itemBinding.categoryImage)
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
