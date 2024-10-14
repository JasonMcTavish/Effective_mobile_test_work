package ru.test.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.test.data.entity.OfferEntity
import ru.test.data.entity.VacancyEntity
import ru.test.data.mapper.AddressMapperImpl
import ru.test.data.mapper.ButtonMapperImpl
import ru.test.data.mapper.ExperienceMapperImpl
import ru.test.data.mapper.OfferMapperImpl
import ru.test.data.mapper.SalaryMapperImpl
import ru.test.data.mapper.VacancyMapperImpl
import ru.test.data.model.Address
import ru.test.data.model.Button
import ru.test.data.model.Experience
import ru.test.data.model.Salary
import ru.test.domain.mapper.AddressMapper
import ru.test.domain.mapper.ButtonMapper
import ru.test.domain.mapper.ExperienceMapper
import ru.test.domain.mapper.OfferMapper
import ru.test.domain.mapper.SalaryMapper
import ru.test.domain.mapper.VacancyMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindOfferMapper(impl: OfferMapperImpl): OfferMapper<OfferEntity>

    @Binds
    @Singleton
    abstract fun bindButtonMapper(impl: ButtonMapperImpl): ButtonMapper<Button>

    @Binds
    @Singleton
    abstract fun bindVacancyMapper(impl: VacancyMapperImpl): VacancyMapper<VacancyEntity>

    @Binds
    @Singleton
    abstract fun bindAddressMapper(impl: AddressMapperImpl): AddressMapper<Address>

    @Binds
    @Singleton
    abstract fun bindExperienceMapper(impl: ExperienceMapperImpl): ExperienceMapper<Experience>

    @Binds
    @Singleton
    abstract fun bindSalaryMapper(impl: SalaryMapperImpl): SalaryMapper<Salary>
}