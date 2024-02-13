package com.graduation.mawruth.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.graduation.domain.model.museum.MuseumDto
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.MuseumItemBinding

class MuseumRecyclerAdapter(var list: List<MuseumDto?>?) :
    RecyclerView.Adapter<MuseumRecyclerAdapter.ViewHolder>() {

    class ViewHolder(var itemBinding: MuseumItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            MuseumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    fun bindMuseumsList(list: List<MuseumDto?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.rate.text = list?.get(position)?.rating.toString()
        holder.itemBinding.museumName.text = list?.get(position)?.name.toString()
        holder.itemBinding.museumLocation.text =
            "${list?.get(position)?.country} ${list?.get(position)?.city}"
        holder.itemBinding.musImage.background =
            ContextCompat.getDrawable(holder.itemBinding.root.context, R.drawable.museum_pic)

        //----------------------------------------------------------------------------------------------------------------------------------
        onMuseumClickListener?.let { onMuseumClickListener ->
            holder.itemBinding.root.setOnClickListener {
                onMuseumClickListener.onClick(list?.get(position)!!, position)
            }
        }

    }

    var onMuseumClickListener: OnMuseumClickListener? = null

    fun interface OnMuseumClickListener {
        fun onClick(museumDto: MuseumDto, position: Int)
    }
}