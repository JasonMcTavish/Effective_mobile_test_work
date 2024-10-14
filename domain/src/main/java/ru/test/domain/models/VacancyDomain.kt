package ru.test.domain.models

import java.util.UUID

data class VacancyDomain(
    val id: UUID,
    val lookingNumber: Int? = null,
    val title: String,
    val address: AddressDomain,
    val company: String,
    val experience: ExperienceDomain,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDomain,
    val schedules: List<String>,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String,
    val questions: List<String>
)


data class AddressDomain(
    val town: String,
    val street: String,
    val house: String,
)

data class ExperienceDomain(
    val previewText: String? = null,
    val text: String,
)

data class SalaryDomain(
    val short: String? = null,
    val full: String,
)