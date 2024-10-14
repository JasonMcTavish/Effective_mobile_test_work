package ru.test.data.repository

import ru.test.data.db.VacancyDao
import ru.test.domain.repository.ChangeVacancyRepository
import java.util.UUID
import javax.inject.Inject

class ChangeVacancyRepositoryImpl @Inject constructor(
    private val vacancyDao: VacancyDao,
) : ChangeVacancyRepository {
    override suspend fun changeFavouriteState(id: UUID) {
        try {
            vacancyDao.changeFavouriteState(id)
        } catch (e: Exception) {
            throw Exception()
        }
    }
}