package com.example.mesibikes.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Bike::class], version = 1)
abstract class BikeDatabase : RoomDatabase() {
    abstract fun bikeDao(): BikeDao
}