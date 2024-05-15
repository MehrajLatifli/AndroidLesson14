package com.example.dummyJSON_Recipes.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String) {
    imageView.loadUrl(url)
}