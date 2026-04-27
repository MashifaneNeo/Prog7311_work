package com.example.balance_wise.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_goals")
data class MonthlyGoal(
    @PrimaryKey(autoGenerate = true) val goal_id: Int = 0,
    @ColumnInfo(name = "month") val month: String,         // e.g. "2025-04"
    @ColumnInfo(name = "min_goal") val minGoal: Double,
    @ColumnInfo(name = "max_goal") val maxGoal: Double
)