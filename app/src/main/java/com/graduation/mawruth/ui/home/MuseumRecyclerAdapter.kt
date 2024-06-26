package com.graduation.mawruth.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.museums.MuseumItem
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.MuseumItemBinding

class MuseumRecyclerAdapter(var list: MutableList<MuseumItem?>?) :
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

    fun bindMuseumsList(list: MutableList<MuseumItem?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemBinding.museumName.text = list?.get(position)?.name.toString()
        holder.itemBinding.museumLocation.text =
            "${list?.get(position)?.city} ${list?.get(position)?.street}"

        list?.get(position)?.images?.get(0)?.imagePath?.let {
            Glide.with(holder.itemView)
                .load(it)
                .into(holder.itemBinding.musImage)
        }
//        Log.d("images", list?.get(0)?.images?.get(0)?.imagePath.toString())
//        holder.itemBinding.musImage.background =
//            ContextCompat.getDrawable(holder.itemBinding.root.context, R.drawable.museum_pic)

        //----------------------------------------------------------------------------------------------------------------------------------
        onMuseumClickListener?.let { onMuseumClickListener ->
            holder.itemBinding.root.setOnClickListener {
                onMuseumClickListener.onClick(list?.get(position)!!, position)
            }
        }
        onLoveClickListener?.let { onLoveClickListener ->
            holder.itemBinding.loveBtn.setOnClickListener {
                onLoveClickListener.onClick(list?.get(position)!!,position)
            }
        }
        if (list?.get(position)?.isFavourite == true) {
            holder.itemBinding.loveBtn.setImageResource(R.drawable.asemheart)
        } else {
            holder.itemBinding.loveBtn.setImageResource(R.drawable.outlined_heart)

        }


    }
fun binditem(museumDto: MuseumItem,position: Int){
    Log.e("list1", list.toString())
    list?.set(position, museumDto)
    Log.e("list2", list.toString())
    notifyDataSetChanged()
}

    fun removeitem(position: Int) {
        list?.removeAt(position)
        notifyDataSetChanged()
    }

    var onMuseumClickListener: OnMuseumClickListener? = null
var onLoveClickListener:OnMuseumClickListener?=null
    fun interface OnMuseumClickListener {
        fun onClick(museumDto: MuseumItem, position: Int)
    }
}