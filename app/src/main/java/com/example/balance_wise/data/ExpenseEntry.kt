package com.example.balance_wise.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "expense_entries",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExpenseEntry(
    @PrimaryKey(autoGenerate = true) val entry_id: Int = 0,
    @ColumnInfo(name = "date") val date: String,           // e.g. "2025-04-20"
    @ColumnInfo(name = "start_time") val startTime: String, // e.g. "08:00"
    @ColumnInfo(name = "end_time") val endTime: String,     // e.g. "09:00"
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo(name = "photo_uri") val photoUri: String? = null  // optional photo
)