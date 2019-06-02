package com.android.weathergojek.screen.component

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.android.weathergojek.BR
import com.android.weathergojek.screen.base.ViewModel

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class RecyclerViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindViewModel(viewModel: ViewModel) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}
