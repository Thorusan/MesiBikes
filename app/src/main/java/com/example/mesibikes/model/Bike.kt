package com.example.mesibikes.db

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "bikes_table")
data class Bike(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    var status: BikeStatus,
    @Nullable
    var user: User?,
    @Nullable
    var lastReservation: LocalDateTime?,
    @Nullable
    var nextReservation: LocalDateTime?,
    var distance: Double,
    val reservationNr: Int
)

enum class BikeStatus(val description: String) {
    AVAILABLE("Na voljo"),
    NOT_AVAILABLE("Izposojeno")
}

@Entity(tableName = "user_table")
data class User(
    val name: String?,
    val surname: String?,
    val department:  String?,
    @Nullable
    val reservationStart: LocalDateTime?,
    @Nullable
    val reservationEnd: LocalDateTime?,
    val distance: Double = 0.0,
    val borrowPurpose: String?
)

