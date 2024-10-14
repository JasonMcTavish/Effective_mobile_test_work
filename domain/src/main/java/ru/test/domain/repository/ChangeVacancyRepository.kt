package ru.test.domain.repository

import java.util.UUID

interface ChangeVacancyRepository {
    suspend fun changeFavouriteState(id: UUID)
}