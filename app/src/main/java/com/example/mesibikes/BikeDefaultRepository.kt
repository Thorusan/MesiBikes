package com.example.mesibikes

import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.BikeDao
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val repositoryModule = module {
    single {
        BikeDefaultRepository(get())
    }
}

class BikeDefaultRepository(private val bikeDao: BikeDao): BikeRepository {

    override suspend fun insertBike(bike: Bike) {
        bikeDao.insert(bike)
    }

    override fun getAllBikes(): Flow<List<Bike>> {
        return bikeDao.getBikes()
    }
}
