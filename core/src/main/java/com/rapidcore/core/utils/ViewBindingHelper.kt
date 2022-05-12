package com.rapidcore.core.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation

object ViewBindingHelper {

    @JvmStatic
    @BindingAdapter(
        value = ["urlImage", "imRes", "placeholder", "progressView", "circleCrop", "cornerRadius"],
        requireAll = false
    )
    fun loadUrlImage(
        view: ImageView,
        urlImage: String?,
        imRes: Int?,
        placeholder: Drawable? = null,
        progressView: ProgressBar? = null,
        circleCrop: Boolean = false,
        cornerRadius: Float?
    ) {
        urlImage?.let { url ->
            view.load(url){
                crossfade(true)
                crossfade(750)
                placeholder?.let {
                    this.placeholder(it)
                }
                scale(Scale.FILL)
                val transform = ArrayList<Transformation>()
                if (circleCrop){
                    transform.add(CircleCropTransformation())
                }
                cornerRadius?.let {
                    transform.add(RoundedCornersTransformation(it))
                }
                transformations(transform)
            }
        }
    }
}