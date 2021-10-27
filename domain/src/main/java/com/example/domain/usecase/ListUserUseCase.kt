package com.example.domain.usecase

import com.example.domain.base.SingleUseCase
import com.example.domain.executor.BaseSchedulerProvider
import com.example.domain.model.Person
import com.example.domain.repository.PersonRepository

open class ListUserUseCase(
    private val repository: PersonRepository,
    scheduler: BaseSchedulerProvider
) : SingleUseCase<List<Person>, Unit>(scheduler) {
    override fun buildUseCaseSingle(params: Unit?) = repository.listPerson()
}
