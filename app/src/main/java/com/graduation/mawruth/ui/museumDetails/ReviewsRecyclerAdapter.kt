package com.graduation.mawruth.ui.museumDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graduation.domain.model.ReviewDto
import com.graduation.mawruth.databinding.ReviewItemBinding

class ReviewsRecyclerAdapter(var list: MutableList<ReviewDto?>?) :
    RecyclerView.Adapter<ReviewsRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ReviewItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ReviewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    fun bindReviewsList(list: MutableList<ReviewDto?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    fun bindSingleReview(review: ReviewDto) {
        this.list?.add(review)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)
        holder.viewBinding.review = item
        holder.viewBinding.executePendingBindings()

    }

}