package com.android.weathergojek.screen.component

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.weathergojek.screen.base.ViewModel
import javax.inject.Inject

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class RecyclerViewAdapterImpl @Inject constructor() : RecyclerView.Adapter<RecyclerViewHolder>(), ListAdapter<Any> {
    var layoutId: Int = 0
    override var items: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if (position < items.size) holder.bindViewModel(getItem(position) as ViewModel)
    }

    override fun getItemCount(): Int = items.size

    override fun addItem(item: Any) {
        items.add(item)
    }

    override fun addItems(items: MutableList<Any>) {
        this.items.addAll(items)
    }

    override fun getItem(position: Int): Any = this.items[position]

    override fun clear() {
        this.items.clear()
    }
}
