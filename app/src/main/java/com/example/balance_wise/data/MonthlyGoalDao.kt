package com.example.balance_wise.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MonthlyGoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(goal: MonthlyGoal)

    @Query("SELECT * FROM monthly_goals WHERE month = :month LIMIT 1")
    suspend fun getGoalForMonth(month: String): MonthlyGoal?
}