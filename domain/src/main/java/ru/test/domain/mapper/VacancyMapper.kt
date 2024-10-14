package ru.test.domain.mapper

import ru.test.domain.models.VacancyDomain

interface VacancyMapper<T> {
    fun mapToDomain(entity: T): VacancyDomain
    fun mapToDomainList(entities: List<T>): List<VacancyDomain>
}