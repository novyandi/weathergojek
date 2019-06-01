package com.android.weathergojek.rx

import io.reactivex.Scheduler

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface AppScheduler {
    fun processScheduler(): Scheduler
    fun androidScheduler(): Scheduler
}
