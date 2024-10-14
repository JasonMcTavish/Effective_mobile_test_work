package ru.test.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.OfferDomain
import ru.test.domain.models.VacancyDomain

interface DataRepository {
    fun getAllOffers(): Flow<List<OfferDomain>>
    fun getAllVacancies(): Flow<List<VacancyDomain>>

    suspend fun getDataFromApi()
}