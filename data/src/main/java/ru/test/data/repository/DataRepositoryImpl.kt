package ru.test.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.test.data.api.ApiService
import ru.test.data.db.OfferDao
import ru.test.data.db.VacancyDao
import ru.test.data.entity.OfferEntity
import ru.test.data.entity.VacancyEntity
import ru.test.domain.mapper.OfferMapper
import ru.test.domain.mapper.VacancyMapper
import ru.test.domain.models.OfferDomain
import ru.test.domain.models.VacancyDomain
import ru.test.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val offerDao: OfferDao,
    private val vacancyDao: VacancyDao,
    private val offerMapper: OfferMapper<OfferEntity>,
    private val vacancyMapper: VacancyMapper<VacancyEntity>,
) : DataRepository {
    override fun getAllOffers(): Flow<List<OfferDomain>> {
        return offerDao.getAll().map { entities ->
            offerMapper.mapToDomainList(entities)
        }
    }

    override fun getAllVacancies(): Flow<List<VacancyDomain>> {
        return vacancyDao.getAll().map { entities ->
            vacancyMapper.mapToDomainList(entities)
        }
    }

    override suspend fun getDataFromApi() {
        try {
            val response = apiService.getData()

            if (!response.isSuccessful) {
                throw Exception()
            }

            val body = response.body() ?: throw Exception()

            val offerList = body.offers
            val vacancyList = body.vacancies

            offerDao.insertAll(offerList.map { OfferEntity.fromDto(it) })
            vacancyDao.insertAll(vacancyList.map { VacancyEntity.fromDto(it) })

        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e)
        }
    }
}