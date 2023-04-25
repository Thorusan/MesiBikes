package com.example.mesibikes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bikes_table")
data class Bike(
    @PrimaryKey(autoGenerate = false)
    var name: String,
    var status: BikeStatus
)

enum class BikeStatus(description: String) {
    AVAILABLE("Na voljo"),
    NOT_AVAILABLE("Izposojeno")
}