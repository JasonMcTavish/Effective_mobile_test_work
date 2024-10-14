package ru.test.domain.usercase

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.VacancyDomain
import ru.test.domain.repository.DataRepository
import javax.inject.Inject

class GetAllVacanciesUseCase @Inject constructor(
    private val repository: DataRepository
) {
    operator fun invoke(): Flow<List<VacancyDomain>> {
        return repository.getAllVacancies()
    }
}