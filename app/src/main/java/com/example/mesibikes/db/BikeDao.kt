package com.example.mesibikes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bike: Bike)

    @Query("DELETE FROM bikes_table")
    suspend fun deleteAllBikes()

    @Query("SELECT * FROM bikes_table")
    fun getBikes(): Flow<List<Bike>>

}