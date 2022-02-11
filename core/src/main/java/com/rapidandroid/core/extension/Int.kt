package com.rapidandroid.core.extension

import android.content.res.Resources

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.month(): String {
    val month = this+1
    return if (month<10) "0$month"
    else month.toString()
}

fun Int.monthPretty(): String {
    val month = listOf("Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agt", "Sep", "Okt", "Nov", "Des")
    if (this > 11) throw Throwable("Month value can't exceed 11")
    return month[this]
}