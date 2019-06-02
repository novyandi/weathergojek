package com.android.weathergojek.screen.binding

import android.databinding.BindingAdapter
import android.widget.TextView

object CustomBinding {
    @JvmStatic
    @BindingAdapter("android:textBinding")
    fun textBinding(view: TextView, resource: ObservableText?) {
        resource?.let {
            val formatArgs = mutableListOf<String>()
            with(it) {
                textArgs.forEach { arg ->
                    if (arg is Int) {
                        formatArgs.add(view.context.getString(arg))
                    } else {
                        formatArgs.add(arg.toString())
                    }
                }
                var stringText = when (val value = get()) {
                    is Int -> view.context.getString(value)
                    is String -> value
                    else -> ""
                }
                if (formatArgs.isNotEmpty())
                    stringText = String.format(stringText, *formatArgs.toTypedArray())
                view.text = stringText
            }
        }
    }
}
