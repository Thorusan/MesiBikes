package com.example.mesibikes.model

import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.BikeDao
import com.example.mesibikes.db.BikeStatus
import com.example.mesibikes.db.Department
import com.example.mesibikes.db.Purpose
import com.example.mesibikes.db.User
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module
import java.time.LocalDateTime

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

val usersList = listOf<User>(
    User(
        1,
        "Tomo ",
        "Krivec",
        Department.DEVELOPMENT,
        null,
        null,
        0.0,
        Purpose.PRIVATE
    ),
    User(
        2,
        "Ales",
        "Kavcic",
        Department.DEVELOPMENT,
        null,
        null,
        0.0,
        Purpose.PRIVATE
    ),
    User(
        3,
        "Janez",
        "Novak",
        Department.MARKETING,
        null,
        null,
        0.0,
        Purpose.PRIVATE
    ),
    User(
        4,
        "Tine",
        "Cencic",
        Department.SALES,
        null,
        null,
        0.0,
        Purpose.PRIVATE
    ),
    User(
        5,
        "Lojze",
        "Kekec",
        Department.SALES,
        LocalDateTime.now(),
        LocalDateTime.now(),
        0.0,
        Purpose.PRIVATE
    ),
    User(
        6,
        "Anze",
        "Pajtler",
        Department.SALES,
        null,
        null,
        0.0,
        Purpose.PRIVATE
    ),
    User(
        7,
        "John",
        "Connor",
        Department.PRODUCTION,
        null,
        null,
        0.0,
        Purpose.PRIVATE
    ),
)

val bikesList = listOf<Bike> (
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
