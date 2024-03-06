package com.graduation.mawruth.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.graduation.mawruth.R

@BindingAdapter("app:Error")
fun bindErrorOnTextField(
    textInputLayout: TextInputLayout,
    errorMessage: String?
) {
    textInputLayout.error = errorMessage
}

@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide
            .with(imageView.context)
            .load(it)
            .placeholder(R.drawable.person)
            .into(imageView)
    }
}
