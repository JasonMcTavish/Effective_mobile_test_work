package ru.test.data.mapper

import ru.test.domain.models.OfferDomain
import ru.test.data.entity.OfferEntity
import ru.test.data.model.Button
import ru.test.domain.mapper.ButtonMapper
import ru.test.domain.mapper.OfferMapper
import javax.inject.Inject

class OfferMapperImpl @Inject constructor(
    private val buttonMapper: ButtonMapper<Button>
) : OfferMapper<OfferEntity> {
    override fun mapToDomain(entity: OfferEntity): OfferDomain {
        return OfferDomain(
            id = entity.id,
            title = entity.title,
            button = buttonMapper.mapToDomain(entity.button),
            link = entity.link
        )
    }

    override fun mapToDomainList(entities: List<OfferEntity>): List<OfferDomain> {
        return entities.map { mapToDomain(it) }
    }
}