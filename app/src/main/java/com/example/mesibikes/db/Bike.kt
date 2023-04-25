package com.example.mesibikes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bikes_table")
data class Bike(
    var title: String,
    var description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0;
}