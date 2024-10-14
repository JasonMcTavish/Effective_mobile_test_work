package ru.test.domain.usercase

import ru.test.domain.repository.ChangeVacancyRepository
import java.util.UUID
import javax.inject.Inject

class ChangeFavouriteUseCase @Inject constructor(
    private val repository: ChangeVacancyRepository
) {
    suspend operator fun invoke(id: UUID) {
        repository.changeFavouriteState(id)
    }
}