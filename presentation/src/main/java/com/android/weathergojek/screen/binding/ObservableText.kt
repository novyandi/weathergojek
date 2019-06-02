package com.android.weathergojek.screen.binding

import android.databinding.BaseObservable

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class ObservableText constructor() : BaseObservable() {
    private var textValue: String = ""
    private var textRes: Int = 0
    val textArgs: MutableList<Any> = mutableListOf()

    constructor(textRes: Int?) : this() {
        this.textRes = textRes ?: 0
    }

    constructor(text: String?) : this() {
        this.textValue = text ?: ""
    }

    fun get(): Any {
        return if (textRes != 0) {
            this.textRes
        } else {
            textValue
        }
    }

    fun set(value: String?) {
        clear()
        this.textValue = value ?: ""
        notifyChange()
    }

    fun set(value: String?, vararg args: Any?) {
        clear()
        this.textValue = value ?: ""
        args.forEach { if (it != null) textArgs.add(it) }
        notifyChange()
    }

    fun set(value: Int?) {
        clear()
        this.textRes = value ?: 0
        notifyChange()
    }

    fun set(value: Int?, vararg args: Any?) {
        clear()
        this.textRes = value ?: 0
        args.forEach { if (it != null) textArgs.add(it) }
        notifyChange()
    }

    fun set(value: Any?) {
        clear()
        when (value) {
            is String -> this.textValue = value
            is Int -> this.textRes = value
        }
        notifyChange()
    }

    fun clear() {
        textArgs.clear()
        textValue = ""
        textRes = 0
    }
}
