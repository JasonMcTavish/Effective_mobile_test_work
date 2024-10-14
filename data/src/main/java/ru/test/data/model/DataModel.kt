package ru.test.data.model

data class DataModel(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>,
)

data class Offer(
    val id: String? = null,
    val title: String,
    val button: Button? = null,
    val link: String,
)

data class Vacancy(
    val id: String,
    val lookingNumber: Int? = null,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String,
    val questions: List<String>
)

data class Button(
    val text: String,
)

data class Address(
    val town: String,
    val street: String,
    val house: String,
)

data class Experience(
    val previewText: String? = null,
    val text: String,
)

data class Salary(
    val short: String? = null,
    val full: String,
)