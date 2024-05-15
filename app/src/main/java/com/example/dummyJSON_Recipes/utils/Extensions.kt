package com.example.dummyJSON_Recipes.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.dummyJSON_Recipes.R

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadUrl(url: String?) {
    Glide
        .with(this)
        .load(url)
        .centerCrop().placeholder(R.drawable.ic_launcher_background)
        .into(this)
}