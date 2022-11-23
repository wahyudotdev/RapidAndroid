package com.rapidcore.core.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

@Suppress("unused")
class NoFilterArrayAdapter<T: Any?> : ArrayAdapter<T?> {

    constructor(context: Context, resource: Int) : super(context, resource)
    constructor(context: Context, resource: Int, objects: List<T>) : super(context, resource, objects)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                return null
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {}
        }
    }
}