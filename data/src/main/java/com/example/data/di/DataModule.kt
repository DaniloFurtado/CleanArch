package com.example.data.di

import com.example.data.repository.PersonRepositoryImp
import com.example.domain.repository.PersonRepository
import org.koin.dsl.module

val dataModule = module {
    single<PersonRepository> { PersonRepositoryImp(get()) }
}
