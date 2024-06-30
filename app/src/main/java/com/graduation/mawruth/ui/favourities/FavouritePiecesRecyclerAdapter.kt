package com.graduation.mawruth.ui.favourities

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.data.model.favourite.FavouritePiecesItemDto
import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.model.Favourite.FavouritePieceitem
import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.mawruth.databinding.PieceRecyclerItemBinding

class FavouritePiecesRecyclerAdapter(var list: List<FavouritePieceitem?>) :
    RecyclerView.Adapter<FavouritePiecesRecyclerAdapter.MyViewHolder>() {
    var itemClick: OnPieceClickListener? = null

    class MyViewHolder(val viewBinding: PieceRecyclerItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PieceRecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    fun bindPiecesList(list: List<FavouritePieceitem?>?) {
        if (list != null) {
            this.list = list
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.viewBinding.pieceTitleTV.text = list.get(0)?.piece?.name
        Glide.with(holder.itemView)
            .load(list.get(0)?.piece?.image.toString())
            .into(holder.viewBinding.pieceImage)
        Log.d("imageUrl", list.get(0)?.piece?.image.toString())

        holder.viewBinding.toPieceDetailsBtn.setOnClickListener {
            itemClick?.onClick(list.get(position)!!, position)
        }

        }

    fun interface OnPieceClickListener {
        fun onClick(data: FavouritePieceitem, position: Int)
    }
}