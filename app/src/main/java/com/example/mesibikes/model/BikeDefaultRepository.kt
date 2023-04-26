package com.example.mesibikes.model

import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.BikeDao
import com.example.mesibikes.db.BikeStatus
import com.example.mesibikes.db.User
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val repositoryModule = module {
    single {
        BikeDefaultRepository(get())
    }
}

class BikeDefaultRepository(private val bikeDao: BikeDao) : BikeRepository {

    override suspend fun insertBike(bike: Bike) {
        bikeDao.insert(bike)
    }

    override suspend fun updateBike(bike: Bike) {
        bikeDao.insert(bike)
    }

    override fun getAllBikes(): Flow<List<Bike>> {
        return bikeDao.getBikes()
    }

    override suspend fun incrementReservations(bikeId: String) {
        bikeDao.incrementReservations(bikeId)
    }
}

val bikesList = listOf(
    Bike(
        "BAJK 1",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
    Bike(
        "BAJK 2",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
    Bike(
        "BAJK 3",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
    Bike(
        "BAJK 4",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
    Bike(
        "BAJK 5",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
    Bike(
        "BAJK 6",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
    Bike(
        "BAJK 7",
        BikeStatus.AVAILABLE,
        null,
        null,
        null,
        0.0,
        0
    ),
)
