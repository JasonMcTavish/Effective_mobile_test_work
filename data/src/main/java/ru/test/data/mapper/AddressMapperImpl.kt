package ru.test.data.mapper

import ru.test.data.model.Address
import ru.test.domain.mapper.AddressMapper
import ru.test.domain.models.AddressDomain
import javax.inject.Inject

class AddressMapperImpl @Inject constructor() : AddressMapper<Address> {
    override fun mapToDomain(entity: Address): AddressDomain {
        return entity.let {
            AddressDomain(it.town, it.street, it.house)
        }
    }

    override fun mapToEntity(domain: AddressDomain): Address {
        return domain.let {
            Address(it.town, it.street, it.house)
        }
    }
}