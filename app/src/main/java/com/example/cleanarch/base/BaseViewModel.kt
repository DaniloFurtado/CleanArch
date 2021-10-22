package com.example.cleanarch.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.base.UseCase

abstract class BaseViewModel(
    vararg useCases: UseCase
) : ViewModel(), LifecycleObserver {
    private val useCaseToDispose = useCases.toList()
    protected val actionErrorModel = MutableLiveData<Throwable>()
    val actionError: LiveData<Throwable> = actionErrorModel

    override fun onCleared() {
        super.onCleared()
        useCaseToDispose.forEach { it.dispose() }
    }
}
