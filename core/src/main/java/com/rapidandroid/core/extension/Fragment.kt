package com.rapidandroid.core.extension

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Ekstensi fragment untuk menjalankan fungsi
 * ketika tombol BACK ditekan
 */
fun Fragment.onBackPress(action: ()-> Unit){
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            action()
        }
    })
}