package ru.test.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.test.data.db.VacancyDao
import ru.test.data.entity.VacancyEntity
import ru.test.domain.mapper.VacancyMapper
import ru.test.domain.models.VacancyDomain
import ru.test.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val vacancyDao: VacancyDao,
    private val vacancyMapper: VacancyMapper<VacancyEntity>,
) : FavouriteRepository {
    override fun getFavouriteVacancies(): Flow<List<VacancyDomain>> {
        return vacancyDao.getFavourite().map { entities ->
            vacancyMapper.mapToDomainList(entities)
        }
    }
}