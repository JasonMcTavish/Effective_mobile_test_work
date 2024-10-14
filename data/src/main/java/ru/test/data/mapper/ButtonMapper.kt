package ru.test.data.mapper

import ru.test.data.model.Button
import ru.test.domain.mapper.ButtonMapper
import ru.test.domain.models.ButtonDomain
import javax.inject.Inject


class ButtonMapperImpl @Inject constructor() : ButtonMapper<Button> {
    override fun mapToDomain(entity: Button?): ButtonDomain? {
        return entity?.let {
            ButtonDomain(it.text)
        }
    }

    override fun mapToEntity(domain: ButtonDomain?): Button? {
        return domain?.let {
            Button(it.text)
        }
    }
}