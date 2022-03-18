package com.rapidcore.core.extension

import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

/**
 * Menampilkan preview halaman selanjutnya di ViewPager2
 */
fun ViewPager2.showHorizontalPreview(offsetDpLeft : Int, offsetDpRight : Int, itemMargin : Int){
    this.apply {
        clipToPadding = false   // allow full width shown with padding
        clipChildren = false    // allow left/right item is not clipped
        offscreenPageLimit = 2  // make sure left/right item is rendered
    }

    // increase this offset to show more of left/right
    val offsetPxLeft = offsetDpLeft.DP
    val offsetPxRight = offsetDpRight.DP
    this.setPadding(offsetPxLeft, 0, offsetPxRight, 0)

    // increase this offset to increase distance between 2 items
    val pageMarginPx = itemMargin.DP
    val marginTransformer = MarginPageTransformer(pageMarginPx)
    this.setPageTransformer(marginTransformer)
}