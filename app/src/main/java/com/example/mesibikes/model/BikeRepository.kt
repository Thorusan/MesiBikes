package com.example.mesibikes.model

import com.example.mesibikes.db.Bike
import kotlinx.coroutines.flow.Flow

interface BikeRepository {
    suspend fun insertBike(bike: Bike)
    fun getAllBikes(): Flow<List<Bike>>
}