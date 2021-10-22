package com.example.domain.repository

import com.example.domain.model.Person
import io.reactivex.rxjava3.core.Single

interface PersonRepository {
    fun listPerson(): Single<List<Person>>
}
