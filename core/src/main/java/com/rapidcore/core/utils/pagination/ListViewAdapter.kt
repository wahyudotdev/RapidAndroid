package com.rapidcore.core.utils.pagination

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rapidcore.core.BR

/**
 * Cara inisialisasi adapter :
 * ```
 * val adapter = ListViewAdapter<ItemOrderBinding, Order>(R.layout.item_order).onClick { pos, data ->
 *      // handle onclick disini
 * }
 * binding.rvOrder.adapter = adapter
 *
 * viewModel.productList.observe(viewLifecycleowner){
 *      adapter.submitList(it)
 * }
 * ```
 * Untuk mengambil list item didalam adapter :
 * ```
 * val orderList = adapter.currentList
 *
 * ```
 */
@Suppress("unused")
open class ListViewAdapter<VB: ViewDataBinding, T: Any>(private val layoutRes: Int) : ListAdapter<T, ListViewAdapter<VB, T>.ItemViewHolder<VB, T>>(
    DiffUtilCallback()
){

    private var onItemClick: ((position: Int, data: T) -> Unit)? = null

    open fun onClick(onItemClick: ((position: Int, data: T) -> Unit)? = null): ListViewAdapter<VB, T> {
        this.onItemClick = onItemClick
        return this
    }

    inner class ItemViewHolder<VB : ViewDataBinding, T: Any?>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T?) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder<VB, T>, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
            onItemClick?.let {
                holder.itemView.setOnClickListener {
                    it(position, item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<VB, T> {
        val binding = DataBindingUtil.inflate<VB>(LayoutInflater.from(parent.context), layoutRes, parent, false)
        return ItemViewHolder(binding)
    }

    class DiffUtilCallback<T: Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return newItem === oldItem
        }
    }
}