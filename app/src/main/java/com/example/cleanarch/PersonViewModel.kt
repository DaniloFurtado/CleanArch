package com.example.cleanarch

import androidx.lifecycle.MutableLiveData
import com.example.cleanarch.base.BaseViewModel
import com.example.domain.model.Person
import com.example.domain.usecase.ListUserUseCase

class PersonViewModel(
    private val listUserUseCase: ListUserUseCase
) : BaseViewModel(listUserUseCase) {
    private val _liveDataLisUsers = MutableLiveData<List<Person>>()
    val liveDataListUsers = _liveDataLisUsers
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    fun requestListPerson() {
        listUserUseCase
            .execute(
                params = null,
                onResult = {
                    _liveDataLisUsers.postValue(it)
                },
                onError = (::onHandleError),
                onStartLoad = {
                    loading.value = true
                },
                onFinishLoad = {
                    loading.value = false
                }
            )
    }

    private fun onHandleError(throwable: Throwable) {
    }
}
