package ru.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.test.data.entity.VacancyEntity
import java.util.UUID

@Dao
interface VacancyDao {
    @Query("SELECT * FROM vacancies_table")
    fun getAll(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM vacancies_table WHERE isFavorite = 1")
    fun getFavourite(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM vacancies_table WHERE id = :id")
    fun getById(id: UUID): VacancyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<VacancyEntity>)

    @Query("UPDATE vacancies_table SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun changeFavouriteState(id: UUID)
}