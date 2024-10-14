package ru.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.test.data.entity.Converters
import ru.test.data.entity.OfferEntity
import ru.test.data.entity.VacancyEntity

@Database(
    entities = [
        OfferEntity::class,
        VacancyEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DataBase: RoomDatabase() {
    abstract fun offerDao(): OfferDao
    abstract fun vacancyDao(): VacancyDao
}