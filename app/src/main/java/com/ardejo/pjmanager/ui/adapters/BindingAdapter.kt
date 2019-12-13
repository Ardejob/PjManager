package com.ardejo.pjmanager.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("glideSrc")
fun ImageView.bindGlideSrc(drawableRes: Int?) {
    if (drawableRes == null) return

    Glide.with(context)
            .load(drawableRes)
            .into(this)
}