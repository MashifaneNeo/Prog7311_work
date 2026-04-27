package com.example.balancewise

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.balancewise.adapter.CategoryTotalAdapter
import com.example.balancewise.data.FakeRepository
import java.util.Calendar

class CategoryTotalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_totals)

        val rvTotals = findViewById<RecyclerView>(R.id.rvCategoryTotals)
        rvTotals.layoutManager = LinearLayoutManager(this)
        rvTotals.adapter = CategoryTotalAdapter(FakeRepository.getCategoryTotals())

        findViewById<Button>(R.id.btnTotalsStartDate).setOnClickListener { pickDate(it as Button) }
        findViewById<Button>(R.id.btnTotalsEndDate).setOnClickListener { pickDate(it as Button) }
    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.rvCategoryTotals).adapter = CategoryTotalAdapter(FakeRepository.getCategoryTotals())
    }

    private fun pickDate(target: Button) {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, { _, y, m, d ->
            target.text = "%02d/%02d/%04d".format(d, m + 1, y)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }
}

