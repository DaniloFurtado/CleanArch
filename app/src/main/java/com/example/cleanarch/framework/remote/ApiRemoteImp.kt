package com.example.cleanarch.framework.remote

import com.example.data.payload.PersonPayLoad
import com.example.data.remote.RemoteAPi
import io.reactivex.rxjava3.core.Single

class ApiRemoteImp : RemoteAPi {
    override fun listPerson(): Single<List<PersonPayLoad>> =
        Single.just(
            listOf(
                PersonPayLoad(
                    "José", "Mariano", "htttp", "Goiania"
                ),
                PersonPayLoad(
                    "Osnir", "Freitas", "htttp", "Goiania"
                ),
                PersonPayLoad(
                    "Batman", "no Robin", "htttp", "Goiania"
                ),
                PersonPayLoad(
                    "Xande", "Avião", "htttp", "Goiania"
                )
            )
        )
}
