package com.rapidcore.core.utils

import android.content.Context

/**
 * Created by tuanchauict on 3/16/16.
 */
@Suppress("unused")
abstract class RotateOrientationEventListener : SimpleOrientationEventListener {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, rate: Int) : super(context, rate)

    override fun onChanged(lastOrientation: Int, orientation: Int) {
        val startDeg =
            if (lastOrientation == 0) if (orientation == 3) 360 else 0 else if (lastOrientation == 1) 90 else if (lastOrientation == 2) 180 else 270 // don't know how, but it works
        val endDeg =
            if (orientation == 0) if (lastOrientation == 1) 0 else 360 else if (orientation == 1) 90 else if (orientation == 2) 180 else 270 // don't know how, but it works
        onRotateChanged(startDeg, endDeg)
    }

    abstract fun onRotateChanged(startDeg: Int, endDeg: Int)
}