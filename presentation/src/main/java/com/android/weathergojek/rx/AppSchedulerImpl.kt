package com.android.weathergojek.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class AppSchedulerImpl : AppScheduler {
    override fun processScheduler(): Scheduler = Schedulers.io()

    override fun androidScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
