package com.example.mesibikes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bike: Bike)

    @Update
    suspend fun update(bike: Bike)


    @Query("DELETE FROM bikes_table")
    suspend fun deleteAllBikes()

    @Query("SELECT * FROM bikes_table")
    fun getBikes(): Flow<List<Bike>>

    @Query("UPDATE bikes_table SET reservationNr = reservationNr + 1 WHERE name = :bikeName")
    suspend fun incrementReservations(bikeName: String)
}