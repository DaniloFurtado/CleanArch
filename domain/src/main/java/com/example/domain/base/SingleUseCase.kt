package com.example.domain.base

import com.example.domain.executor.BaseSchedulerProvider
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<T, in Params> constructor(
    private val schedulerProvider: BaseSchedulerProvider
) : UseCase() {
    protected abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open operator fun invoke(
        params: Params? = null,
        onResult: (T) -> Unit,
        onError: (e: Throwable) -> Unit,
        onStartProcess: (() -> Unit)? = null,
        onFinishProcess: (() -> Unit)? = null
    ) {
        this.buildUseCaseSingle(params)
            .subscribeOn(schedulerProvider.executionThread())
            .observeOn(schedulerProvider.postThread())
            .doOnSubscribe { onStartProcess?.invoke() }
            .doOnEvent { _, _ -> onFinishProcess?.invoke() }
            .subscribe({
                onResult.invoke(it)
            }, {
                onError.invoke(it)
            })
            .addDisposable()
    }
}
