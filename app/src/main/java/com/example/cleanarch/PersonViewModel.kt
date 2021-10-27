package com.example.cleanarch

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.example.cleanarch.base.BaseViewModel
import com.example.domain.model.Person
import com.example.domain.usecase.ListUserUseCase

class PersonViewModel(
    private val listUserUseCase: ListUserUseCase
) : BaseViewModel(listUserUseCase) {
    private val _liveDataLisUsers = MutableLiveData<List<Person>>()
    val liveDataListUsers: LiveData<List<Person>> = _liveDataLisUsers
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onInitViewModel() {
        requestListPerson()
    }

    private fun requestListPerson() {
        listUserUseCase
            .execute(
                params = null,
                onResult = { _liveDataLisUsers.postValue(it) },
                onError = (::onHandleError),
                onStartLoad = { _loading.postValue(true) },
                onFinishLoad = { _loading.postValue(false) }
            )
    }

    private fun onHandleError(throwable: Throwable) {
        actionErrorModel.postValue(throwable)
    }
}
