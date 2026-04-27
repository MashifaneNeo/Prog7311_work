package com.example.balancewise.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val category_id: Int = 0,
    @ColumnInfo(name = "name") val name: String  // e.g. "Rent", "Phone Bill", "WiFi"
)