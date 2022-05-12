package com.rapidcore.core.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation

object ViewBindingHelper {

    @JvmStatic
    @BindingAdapter(
        value = ["urlImage", "imRes", "placeholder", "progressView", "circleCrop"],
        requireAll = false
    )
    fun loadUrlImage(
        view: ImageView,
        urlImage: String?,
        imRes: Int?,
        placeholder: Drawable? = null,
        progressView: ProgressBar? = null,
        circleCrop: Boolean = false
    ) {
        urlImage?.let { url ->
            view.load(url){
                crossfade(true)
                crossfade(750)
                placeholder?.let {
                    this.placeholder(it)
                }
                scale(Scale.FILL)
                if (circleCrop){
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}