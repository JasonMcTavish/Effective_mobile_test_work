package ru.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.test.data.entity.OfferEntity

@Dao
interface OfferDao {
    @Query("SELECT * FROM offers_table")
    fun getAll(): Flow<List<OfferEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<OfferEntity>)
}