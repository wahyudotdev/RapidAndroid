package com.rapidcore.core.extension

import android.content.Context
import com.rapidcore.core.R
import com.rapidcore.core.utils.NoFilterArrayAdapter


@Suppress("unused")
fun <T : Any> Iterable<T>.dropdown(
    context: Context,
    layoutRes: Int = R.layout.dropdown_item
): NoFilterArrayAdapter<T> {
    return NoFilterArrayAdapter(context, layoutRes, this.toList())
}
