package com.example.balancewise.data

import com.example.balancewise.model.CategoryTotalItem
import com.example.balancewise.model.ExpenseItem

object FakeRepository {
    val categories = mutableListOf("Groceries", "Transport", "Entertainment", "Rent")
    val expenses = mutableListOf<ExpenseItem>()

    var minGoal: Int = 500
    var maxGoal: Int = 5000

    fun addCategory(name: String) {
        if (name.isNotBlank() && !categories.contains(name)) categories.add(name)
    }

    fun addExpense(item: ExpenseItem) {
        expenses.add(item)
    }

    fun getCategoryTotals(): List<CategoryTotalItem> {
        return expenses
            .groupBy { it.category }
            .map { CategoryTotalItem(it.key, it.value.sumOf { exp -> exp.amount }) }
    }

    fun totalSpent(): Double = expenses.sumOf { it.amount }
}