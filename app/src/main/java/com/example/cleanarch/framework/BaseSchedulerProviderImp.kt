package com.example.cleanarch.framework

import com.example.domain.executor.BaseSchedulerProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class BaseSchedulerProviderImp : BaseSchedulerProvider {
    override fun executionThread(): Scheduler = Schedulers.io()

    override fun postThread(): Scheduler = AndroidSchedulers.mainThread()
}