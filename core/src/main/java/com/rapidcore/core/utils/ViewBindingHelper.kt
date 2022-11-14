package com.rapidcore.core.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.google.android.material.textfield.TextInputLayout

@Suppress("unused")
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

    @JvmStatic
    @BindingAdapter("emptyFieldError")
    fun emptyFieldError(et: TextInputLayout, emptyFieldError: String?) {
        emptyFieldError?.let {
            et.editText?.doOnTextChanged { text, _, _, _ ->
                setEtError(et, text.isNullOrEmpty(), emptyFieldError)
            }
            et.editText?.setOnFocusChangeListener { _, _ ->
                setEtError(et, et.editText?.text?.isEmpty() == true, emptyFieldError)
            }
        }
    }
    private fun setEtError(et: TextInputLayout, error: Boolean, message: String?){
        if (error){
            et.isErrorEnabled = true
            et.errorIconDrawable = null
            et.error = message
            et.boxStrokeErrorColor = ColorStateList.valueOf(Color.RED)
        } else {
            et.isErrorEnabled = false
            et.error = null
        }
    }
}