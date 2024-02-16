package com.graduation.mawruth.ui.museumDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.mawruth.databinding.PieceRecyclerItemBinding

class PiecesAdapter(val list: List<PieceData>) :
    RecyclerView.Adapter<PiecesAdapter.MyViewHolder>() {
    var itemClick: OnPieceClickListener? = null

    class MyViewHolder(val viewBinding: PieceRecyclerItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PieceRecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.viewBinding.apply {
            pieceTitleTV.text = item.pieceTitle
            pieceAgeTV.text = item.pieceAge
            pieceImage.setImageResource(item.image!!)
            itemClick.let {
                holder.viewBinding.toPieceDetailsBtn.setOnClickListener {
                    itemClick?.onClick(list[position], position)
                }
            }


        }
    }

    fun interface OnPieceClickListener {
        fun onClick(data: PieceData, position: Int)
    }
}