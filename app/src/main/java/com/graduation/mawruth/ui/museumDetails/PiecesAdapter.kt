package com.graduation.mawruth.ui.museumDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.mawruth.databinding.PieceRecyclerItemBinding

class PiecesAdapter(var list: List<PiecesItem?>) :
    RecyclerView.Adapter<PiecesAdapter.MyViewHolder>() {
    var itemClick: OnPieceClickListener? = null

    class MyViewHolder(val viewBinding: PieceRecyclerItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PieceRecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    fun bindPiecesList(list: List<PiecesItem?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.viewBinding.pieceTitleTV.text = item?.name.toString()
        Glide.with(holder.itemView)
            .load(item?.image.toString())
            .into(holder.viewBinding.pieceImage)
        Log.d("imageUrl", item?.image.toString())
        holder.viewBinding.toPieceDetailsBtn.setOnClickListener {

            itemClick?.onClick(item!!, position)

        }

        }

    fun interface OnPieceClickListener {
        fun onClick(data: PiecesItem, position: Int)
    }
}