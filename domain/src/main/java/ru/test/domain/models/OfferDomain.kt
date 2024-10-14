package ru.test.domain.models

data class OfferDomain(
    val id: String?,
    val title: String,
    val button: ButtonDomain?,
    val link: String,
)

data class ButtonDomain(
    val text: String,
)
