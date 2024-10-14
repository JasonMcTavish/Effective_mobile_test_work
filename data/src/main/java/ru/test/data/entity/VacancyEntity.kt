package ru.test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.test.data.model.Address
import ru.test.data.model.Experience
import ru.test.data.model.Salary
import ru.test.data.model.Vacancy
import java.util.UUID

@Entity(tableName = "vacancies_table")
class VacancyEntity(
    @PrimaryKey val id: UUID,
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
) {
    fun toDto() = Vacancy(
        id.toString(),
        lookingNumber,
        title,
        address,
        company,
        experience,
        publishedDate,
        isFavorite,
        salary,
        schedules,
        appliedNumber,
        description,
        responsibilities,
        questions
    )

    companion object {
        fun fromDto(dto: Vacancy) = with(dto) {
            VacancyEntity(
                UUID.fromString(id),
                lookingNumber,
                title,
                address,
                company,
                experience,
                publishedDate,
                isFavorite,
                salary,
                schedules,
                appliedNumber,
                description,
                responsibilities,
                questions
            )
        }
    }
}