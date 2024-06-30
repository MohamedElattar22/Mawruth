package com.graduation.mawruth.ui.islamicmuseum.fragments.home.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.collection.CollectionsItem
import com.graduation.mawruth.databinding.MuseumCollectionItemBinding

class CollectionsAdapter(var list: List<CollectionsItem?>) :
    RecyclerView.Adapter<CollectionsAdapter.ViewHolder>() {

    class ViewHolder(var itemBinding: MuseumCollectionItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            MuseumCollectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    fun bindHallsList(list: List<CollectionsItem?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun interface OnCollectionListener {
        fun onClick(collectionData: CollectionsItem, position: Int)
    }

    var onCollectionClickListener: OnCollectionListener? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val collection = list[position]
        holder.itemBinding.categoryName.text = collection?.name
        Glide.with(holder.itemView)
            .load(collection?.image)
            .into(holder.itemBinding.categoryImage)


        onCollectionClickListener.let {
            holder.itemBinding.root.setOnClickListener {
                onCollectionClickListener?.onClick(list[position]!!, position)
            }
        }
    }
}