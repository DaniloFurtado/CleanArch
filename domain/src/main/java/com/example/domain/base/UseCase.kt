package com.example.domain.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class UseCase {
    private var lastDisposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    private fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }

    protected fun Disposable.addDisposable() {
        disposeLast()
        lastDisposable = this
        lastDisposable?.let { compositeDisposable.add(it) }
    }
}
