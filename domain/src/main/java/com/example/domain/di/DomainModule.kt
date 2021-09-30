package com.example.domain.di

import com.example.domain.usecase.ListUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { ListUserUseCase(get(), get()) }
}
