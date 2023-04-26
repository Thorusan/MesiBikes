package com.example.mesibikes.db

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class UserConverter {

    @TypeConverter
    fun fromString(value: String): User? {
        val parts = value.split(",")

        if (parts[0] == "null") return null

        //val id = Integer.parseInt(parts[0])
        val name = parts[0]
        val surname = parts[1]
        val department = parts[2]
        val reservationStart = LocalDateTimeConverter().toLocalDateTime(parts[3])
        val reservationEnd = LocalDateTimeConverter().toLocalDateTime(parts[4])
        val distance = parts[5].toDouble()
        val borrowPurpose = parts[6]

        return User(
            //id,
            name,
            surname,
            department,
            reservationStart,
            reservationEnd,
            distance,
            borrowPurpose
        )
    }

    @TypeConverter
    fun toString(value: User?): String {
        return "${value?.name}," +
                "${value?.surname}," +
                "${value?.department}," +
                "${value?.reservationStart?.toString()}," +
                "${value?.reservationEnd?.toString()}," +
                "${value?.distance?.toString()}," +
                "${value?.borrowPurpose}"
    }
}

class BikeStatusConverter {
    @TypeConverter
    fun fromString(value: String?): BikeStatus {
        return BikeStatus.valueOf(value.toString())
    }

    @TypeConverter
    fun toString(value: BikeStatus): String {
        return value.toString()
    }
}

class LocalDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            return formatter.parse(value, LocalDateTime::from)
        }
    }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }
}
