package com.rapidcore.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

@Suppress("unused")
abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes private val layoutRes: Int): Fragment() {

    @Suppress("private")
    protected lateinit var binding: VB

    open var title: String = ""
    open var hasLoadedOnce = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}