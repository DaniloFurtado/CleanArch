package com.example.cleanarch.di

import com.example.cleanarch.PersonViewModel
import com.example.cleanarch.framework.BaseSchedulerProviderImp
import com.example.cleanarch.framework.remote.ApiRemoteImp
import com.example.data.remote.RemoteAPi
import com.example.domain.executor.BaseSchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<BaseSchedulerProvider> { BaseSchedulerProviderImp() }

    single<RemoteAPi> { ApiRemoteImp() }
    viewModel { PersonViewModel(get()) }
}
