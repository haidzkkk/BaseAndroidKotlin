package com.app.motel.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@SuppressLint("CheckResult")
fun ImageView.show(
    url: String?,
    scaleType: BitmapTransformation = CenterCrop(),
    borderRadius: Int = 1,
    placeholder: Int? = null,
    onLoadFailed: (() -> Unit)? = null,
    onLoadSuccess: (() -> Unit)? = null
){
    Glide.with(this.context)
        .load(url)
        .transform(scaleType, RoundedCorners(borderRadius))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                onLoadFailed?.invoke()
                return false
            }
            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                onLoadSuccess?.invoke()
                return false
            }
        })
        .apply {
            if(placeholder != null){
                this.placeholder(placeholder)
            }
        }.into(this)
}