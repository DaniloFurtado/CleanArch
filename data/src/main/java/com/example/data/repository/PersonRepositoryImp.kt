package com.example.data.repository

import com.example.data.extension.convertToModel
import com.example.data.remote.RemoteAPi
import com.example.domain.model.Person
import com.example.domain.repository.PersonRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class PersonRepositoryImp(
    private val api: RemoteAPi
) : PersonRepository {
    override fun listPerson(): Single<List<Person>> =
        api.listPerson()
            .map { list ->
                list.map { person ->
                    person.convertToModel()
                }
            }
            .delay(1500, TimeUnit.MILLISECONDS)
}
