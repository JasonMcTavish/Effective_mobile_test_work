package ru.test.data.mapper

import ru.test.data.model.Salary
import ru.test.domain.mapper.SalaryMapper
import ru.test.domain.models.SalaryDomain
import javax.inject.Inject

class SalaryMapperImpl @Inject constructor() : SalaryMapper<Salary> {
    override fun mapToDomain(entity: Salary): SalaryDomain {
        return entity.let {
            SalaryDomain(it.short, it.full)
        }
    }

    override fun mapToEntity(domain: SalaryDomain): Salary {
        return domain.let {
            Salary(it.short, it.full)
        }
    }
}