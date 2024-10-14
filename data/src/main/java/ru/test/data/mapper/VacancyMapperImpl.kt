package ru.test.data.mapper

import ru.test.data.entity.VacancyEntity
import ru.test.data.model.Address
import ru.test.data.model.Experience
import ru.test.data.model.Salary
import ru.test.domain.mapper.AddressMapper
import ru.test.domain.mapper.ExperienceMapper
import ru.test.domain.mapper.SalaryMapper
import ru.test.domain.mapper.VacancyMapper
import ru.test.domain.models.VacancyDomain
import javax.inject.Inject

class VacancyMapperImpl @Inject constructor(
    private val addressMapper: AddressMapper<Address>,
    private val experienceMapper: ExperienceMapper<Experience>,
    private val salaryMapper: SalaryMapper<Salary>,
) : VacancyMapper<VacancyEntity> {
    override fun mapToDomain(entity: VacancyEntity): VacancyDomain {
        return VacancyDomain(
            id = entity.id,
            lookingNumber = entity.lookingNumber,
            title = entity.title,
            address = addressMapper.mapToDomain(entity.address),
            company = entity.company,
            experience = experienceMapper.mapToDomain(entity.experience),
            publishedDate = entity.publishedDate,
            isFavorite = entity.isFavorite,
            salary = salaryMapper.mapToDomain(entity.salary),
            schedules = entity.schedules,
            appliedNumber = entity.appliedNumber,
            description = entity.description,
            responsibilities = entity.responsibilities,
            questions = entity.questions
        )
    }

    override fun mapToDomainList(entities: List<VacancyEntity>): List<VacancyDomain> {
        return entities.map { mapToDomain(it) }
    }
}