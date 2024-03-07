package com.graduation.mawruth.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.categories.CategoriesDtoItem
import com.graduation.mawruth.databinding.CategoryItemBinding

class CategoriesRecyclerAdapter(var list: List<CategoriesDtoItem?>?) :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder>() {
    var onTypeClickListener: OnTypeClickListener? = null
    class ViewHolder(val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    fun bindMuseumsList(list: List<CategoriesDtoItem?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)
        Glide.with(holder.itemView)
            .load(item?.image)
            .into(holder.itemBinding.categoryImage)

        holder.itemBinding.categoryName.text = item?.name

        onTypeClickListener?.let { onTypeClick ->
            holder.itemBinding.root.setOnClickListener {
                onTypeClick.onClick(item!!, position)
            }
        }
    }

    fun interface OnTypeClickListener {
        fun onClick(categoriesDtoItem: CategoriesDtoItem, position: Int)
    }
}