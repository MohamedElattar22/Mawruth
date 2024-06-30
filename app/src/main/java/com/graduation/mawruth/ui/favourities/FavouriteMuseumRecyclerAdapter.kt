package com.graduation.mawruth.ui.favourities



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.Favourite.FavouriteMuseumItem
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.MuseumItemBinding

class FavouriteMuseumRecyclerAdapter(var list: List<FavouriteMuseumItem?>?) :
    RecyclerView.Adapter<FavouriteMuseumRecyclerAdapter.ViewHolder>() {

    class ViewHolder(var itemBinding: MuseumItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            MuseumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    fun bindMuseumsList(list: List<FavouriteMuseumItem?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemBinding.museumName.text = list?.get(position)?.museum?.name.toString()
        holder.itemBinding.museumLocation.text = "${list?.get(position)?.museum?.city} ${list?.get(position)?.museum?.street}"

        list?.get(position)?.museum?.images?.get(0)?.imagePath?.let {
            Glide.with(holder.itemView)
                .load(it)
                .into(holder.itemBinding.musImage)
        }
holder.itemBinding.loveBtn.setImageResource(R.drawable.asemheart)
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

    }

    var onMuseumClickListener: OnMuseumClickListener? = null
    var onLoveClickListener:OnMuseumClickListener?=null
    fun interface OnMuseumClickListener {
        fun onClick(museumDto: FavouriteMuseumItem, position: Int)
    }
}