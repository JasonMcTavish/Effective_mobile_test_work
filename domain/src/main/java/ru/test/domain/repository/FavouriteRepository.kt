package ru.test.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.VacancyDomain

interface FavouriteRepository {
    fun getFavouriteVacancies(): Flow<List<VacancyDomain>>
}