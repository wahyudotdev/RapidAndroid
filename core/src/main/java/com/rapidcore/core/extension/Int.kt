package com.rapidcore.core.extension

import android.content.res.Resources

val Int.DP : Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

@Suppress("unused")
val Int.PX : Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()