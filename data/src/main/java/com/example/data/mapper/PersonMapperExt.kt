package com.example.data.mapper

import com.example.data.payload.PersonPayLoad
import com.example.domain.model.Person

fun PersonPayLoad.convertToModel() = Person(
    firstName = this.firstName,
    secondName = this.secondName,
    urlPhoto = this.urlPhoto,
    city = this.city
)

fun Person.convertToPayLoad() = PersonPayLoad(
    firstName = this.firstName,
    secondName = this.secondName,
    urlPhoto = this.urlPhoto,
    city = this.city
)
