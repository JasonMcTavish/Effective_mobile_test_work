package ru.test.domain.mapper

import ru.test.domain.models.OfferDomain

interface OfferMapper<T> {
    fun mapToDomain(entity: T): OfferDomain
    fun mapToDomainList(entities: List<T>): List<OfferDomain>
}