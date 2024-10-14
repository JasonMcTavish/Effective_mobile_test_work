package ru.test.data.mapper

import ru.test.data.model.Experience
import ru.test.domain.mapper.ExperienceMapper
import ru.test.domain.models.ExperienceDomain
import javax.inject.Inject

class ExperienceMapperImpl @Inject constructor() : ExperienceMapper<Experience> {
    override fun mapToDomain(entity: Experience): ExperienceDomain {
        return entity.let {
            ExperienceDomain(it.previewText, it.text)
        }
    }

    override fun mapToEntity(domain: ExperienceDomain): Experience {
        return domain.let {
            Experience(it.previewText, it.text)
        }
    }
}