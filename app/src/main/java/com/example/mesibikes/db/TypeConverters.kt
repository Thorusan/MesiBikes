package com.example.mesibikes.db

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class UserConverter {

    @TypeConverter
    fun fromString(value: String): User? {
        val parts = value.split(",")

        if (parts[0] == "null") return null

        val id = Integer.parseInt(parts[0])
        val name = parts[1]
        val surname = parts[2]
        val department: Department = Department.valueOf(parts[3])
        val reservationStart = LocalDateTimeConverter().toLocalDateTime(parts[4])
        val reservationEnd = LocalDateTimeConverter().toLocalDateTime(parts[5])
        val distance = parts[6].toDouble()
        val borrowPurpose = Purpose.valueOf(parts[7])

        return User(
            id,
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
        return "${value?.id}," +
                "${value?.name}," +
                "${value?.surname}," +
                "${value?.department}," +
                "${value?.reservationStart?.toString()}," +
                "${value?.reservationEnd?.toString()}"
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

class DepartmentConverter {
    @TypeConverter
    fun fromString(value: String?): Department {
        return Department.valueOf(value.toString())
    }

    @TypeConverter
    fun toString(value: Department): String {
        return value.toString()
    }
}


class PurposeConverter {
    @TypeConverter
    fun fromString(value: String?): Purpose {
        return Purpose.valueOf(value.toString())
    }

    @TypeConverter
    fun toString(value: Purpose): String {
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
