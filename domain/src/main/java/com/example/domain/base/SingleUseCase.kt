package com.example.domain.base

import com.example.domain.executor.BaseSchedulerProvider
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<T, in Params> constructor(
    private val schedulerProvider: BaseSchedulerProvider
) : UseCase() {
    abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(
        params: Params? = null,
        onResult: (T) -> Unit,
        onError: (e: Throwable) -> Unit,
        onStartLoad: (() -> Unit)? = null,
        onFinishLoad: (() -> Unit)? = null
    ) {
        this.buildUseCaseSingle(params)
            .subscribeOn(schedulerProvider.executionThread())
            .observeOn(schedulerProvider.postThread())
            .doOnSubscribe { onStartLoad?.invoke() }
            .doOnEvent { _, _ -> onFinishLoad?.invoke() }
            .subscribe({
                onResult.invoke(it)
            }, {
                onError.invoke(it)
            })
            .addDisposable()
    }
}
