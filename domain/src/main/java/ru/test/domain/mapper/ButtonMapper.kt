package ru.test.domain.mapper

import ru.test.domain.models.ButtonDomain

interface ButtonMapper<T> {
    fun mapToDomain(entity: T?): ButtonDomain?
    fun mapToEntity(domain: ButtonDomain?): T?
}