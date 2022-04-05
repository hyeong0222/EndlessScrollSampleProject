package com.example.endlessscrollsampleproject.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("showUserImage")
fun showUserImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}