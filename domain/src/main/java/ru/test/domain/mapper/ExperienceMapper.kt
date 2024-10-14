package ru.test.domain.mapper

import ru.test.domain.models.ExperienceDomain

interface ExperienceMapper<T> {
    fun mapToDomain(entity: T): ExperienceDomain
    fun mapToEntity(domain: ExperienceDomain): T
}