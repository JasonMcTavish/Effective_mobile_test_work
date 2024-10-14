package ru.test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.test.data.model.Button
import ru.test.data.model.Offer

@Entity(tableName = "offers_table")
class OfferEntity (
    val id: String?,
    @PrimaryKey
    val title: String,
    val button: Button?,
    val link: String,
) {
    fun toDto() = Offer(id, title, button, link)

    companion object {
        fun fromDto(dto: Offer) = with(dto) {
            OfferEntity(id, title, button, link)
        }
    }
}