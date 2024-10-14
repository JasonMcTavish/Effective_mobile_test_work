package ru.test.domain.mapper

import ru.test.domain.models.SalaryDomain

interface SalaryMapper<T> {
    fun mapToDomain(entity: T): SalaryDomain
    fun mapToEntity(domain: SalaryDomain): T
}