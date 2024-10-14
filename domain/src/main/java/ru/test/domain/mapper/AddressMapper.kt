package ru.test.domain.mapper

import ru.test.domain.models.AddressDomain

interface AddressMapper<T> {
    fun mapToDomain(entity: T): AddressDomain
    fun mapToEntity(domain: AddressDomain): T
}