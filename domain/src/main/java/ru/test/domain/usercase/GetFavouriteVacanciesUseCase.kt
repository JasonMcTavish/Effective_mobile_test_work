package ru.test.domain.usercase

import kotlinx.coroutines.flow.Flow
import ru.test.domain.models.VacancyDomain
import ru.test.domain.repository.FavouriteRepository
import javax.inject.Inject

class GetFavouriteVacanciesUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(): Flow<List<VacancyDomain>> {
        return repository.getFavouriteVacancies()
    }
}