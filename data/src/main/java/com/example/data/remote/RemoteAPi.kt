package com.example.data.remote

import com.example.data.payload.PersonPayLoad
import io.reactivex.rxjava3.core.Single

interface RemoteAPi {
    fun listPerson(): Single<List<PersonPayLoad>>
}
