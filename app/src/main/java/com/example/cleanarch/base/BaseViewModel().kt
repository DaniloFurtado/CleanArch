package com.example.cleanarch.base

import androidx.lifecycle.ViewModel
import com.example.domain.base.UseCase

abstract class BaseViewModel(
    vararg useCases: UseCase
) : ViewModel() {
    private val useCaseToDispose = useCases.toList()

    override fun onCleared() {
        super.onCleared()
        useCaseToDispose.forEach { it.dispose() }
    }
}
