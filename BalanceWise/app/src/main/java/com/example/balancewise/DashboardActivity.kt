package com.example.balancewise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.balancewise.data.FakeRepository

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val totalSpent = findViewById<TextView>(R.id.tvTotalSpent)
        val minGoal = findViewById<TextView>(R.id.tvMinGoal)
        val maxGoal = findViewById<TextView>(R.id.tvMaxGoal)

        totalSpent.text = "R %.2f".format(FakeRepository.totalSpent())
        minGoal.text = "Min Goal: R ${FakeRepository.minGoal}"
        maxGoal.text = "Max Goal: R ${FakeRepository.maxGoal}"

        findViewById<Button>(R.id.btnAddCategory).setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity::class.java))
        }
        findViewById<Button>(R.id.btnAddExpense).setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }
        findViewById<Button>(R.id.btnGoals).setOnClickListener {
            startActivity(Intent(this, GoalsActivity::class.java))
        }
        findViewById<Button>(R.id.btnExpenses).setOnClickListener {
            startActivity(Intent(this, ExpenseListActivity::class.java))
        }
        findViewById<Button>(R.id.btnCategoryTotals).setOnClickListener {
            startActivity(Intent(this, CategoryTotalsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.tvTotalSpent).text = "R %.2f".format(FakeRepository.totalSpent())
        findViewById<TextView>(R.id.tvMinGoal).text = "Min Goal: R ${FakeRepository.minGoal}"
        findViewById<TextView>(R.id.tvMaxGoal).text = "Max Goal: R ${FakeRepository.maxGoal}"
    }
}