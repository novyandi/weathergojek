package com.android.weathergojek.screen.component

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface ListAdapter<I> {
    var items: MutableList<I>

    fun addItem(item: I)
    fun addItems(items: MutableList<I>)
    fun getItem(position: Int): I
    fun clear()
}
