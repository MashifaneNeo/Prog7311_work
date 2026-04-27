package com.example.balance_wise.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseEntryDao {

    @Insert
    suspend fun insert(entry: ExpenseEntry)

    // Get all entries within a date range (user-selectable period)
    @Query("SELECT * FROM expense_entries WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    suspend fun getEntriesByPeriod(startDate: String, endDate: String): List<ExpenseEntry>

    // Get total amount spent per category within a date range
    @Query("""
    SELECT category_id as categoryId, SUM(amount) as total 
    FROM expense_entries 
    WHERE date BETWEEN :startDate AND :endDate 
    GROUP BY category_id
""")
    suspend fun getTotalPerCategory(startDate: String, endDate: String): List<CategoryTotal>
    @Query("DELETE FROM expense_entries WHERE entry_id = :id")
    suspend fun deleteById(id: Int)
}

// Helper data class for category totals query
data class CategoryTotal(
    val categoryId: Int,
    val total: Double
)