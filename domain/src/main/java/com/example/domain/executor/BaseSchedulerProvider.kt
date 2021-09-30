package com.example.domain.executor

import io.reactivex.rxjava3.core.Scheduler

interface BaseSchedulerProvider {
    fun executionThread(): Scheduler
    fun postThread(): Scheduler
}
