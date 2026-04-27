package com.example.balancewise

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.balancewise.adapter.ExpenseAdapter
import com.example.balancewise.data.FakeRepository
import java.util.Calendar

class ExpenseListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        val rvExpenses = findViewById<RecyclerView>(R.id.rvExpenses)
        val btnStartDate = findViewById<Button>(R.id.btnStartDate)
        val btnEndDate = findViewById<Button>(R.id.btnEndDate)
        val tvTotal = findViewById<TextView>(R.id.tvExpenseListTotal)

        rvExpenses.layoutManager = LinearLayoutManager(this)
        rvExpenses.adapter = ExpenseAdapter(FakeRepository.expenses)
        tvTotal.text = "Total: R %.2f".format(FakeRepository.totalSpent())

        btnStartDate.setOnClickListener { pickDate(btnStartDate) }
        btnEndDate.setOnClickListener { pickDate(btnEndDate) }

        findViewById<Button>(R.id.btnOpenTotals).setOnClickListener {
            startActivity(Intent(this, CategoryTotalsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.rvExpenses).adapter = ExpenseAdapter(FakeRepository.expenses)
        findViewById<TextView>(R.id.tvExpenseListTotal).text = "Total: R %.2f".format(FakeRepository.totalSpent())
    }

    private fun pickDate(target: Button) {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, { _, y, m, d ->
            target.text = "%02d/%02d/%04d".format(d, m + 1, y)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }
}
