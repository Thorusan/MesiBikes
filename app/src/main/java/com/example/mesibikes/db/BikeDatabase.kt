package com.example.mesibikes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(
    LocalDateTimeConverter::class,
    BikeStatusConverter::class,
    UserConverter::class
)
@Database(entities = [Bike::class], version = 1)
abstract class BikeDatabase : RoomDatabase() {
    abstract fun bikeDao(): BikeDao
}