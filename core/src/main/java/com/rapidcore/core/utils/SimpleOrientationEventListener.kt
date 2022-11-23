package com.rapidcore.core.utils

import android.content.Context
import android.view.OrientationEventListener


/**
 * Created by tuanchauict on 3/16/16.
 */
abstract class SimpleOrientationEventListener : OrientationEventListener {
    private var lastOrientation = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, rate: Int) : super(context, rate)

    override fun onOrientationChanged(orientation: Int) {
        if (orientation < 0) {
            return  // Flip screen, Not take account
        }
        val curOrientation: Int = if (orientation <= 45) {
            ORIENTATION_PORTRAIT
        } else if (orientation <= 135) {
            ORIENTATION_LANDSCAPE_REVERSE
        } else if (orientation <= 225) {
            ORIENTATION_PORTRAIT_REVERSE
        } else if (orientation <= 315) {
            ORIENTATION_LANDSCAPE
        } else {
            ORIENTATION_PORTRAIT
        }
        if (curOrientation != lastOrientation) {
            onChanged(lastOrientation, curOrientation)
            lastOrientation = curOrientation
        }
    }

    abstract fun onChanged(lastOrientation: Int, orientation: Int)

    companion object {
        const val ORIENTATION_PORTRAIT = 0
        const val ORIENTATION_LANDSCAPE = 1
        const val ORIENTATION_PORTRAIT_REVERSE = 2
        const val ORIENTATION_LANDSCAPE_REVERSE = 3
    }
}