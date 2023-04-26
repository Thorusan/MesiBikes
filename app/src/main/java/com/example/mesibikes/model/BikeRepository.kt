package com.example.mesibikes.model

import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.User
import kotlinx.coroutines.flow.Flow

interface BikeRepository {
    suspend fun insertBike(bike: Bike)
    fun getAllBikes(): Flow<List<Bike>>

    suspend fun incrementReservations(bikeId: String)
    suspend fun updateBike(bike: Bike)
}