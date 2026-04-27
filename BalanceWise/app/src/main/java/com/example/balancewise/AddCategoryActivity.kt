package com.example.balancewise

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balancewise.data.FakeRepository

class AddCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        val etCategoryName = findViewById<EditText>(R.id.etCategoryName)
        val btnSaveCategory = findViewById<Button>(R.id.btnSaveCategory)

        btnSaveCategory.setOnClickListener {
            val name = etCategoryName.text.toString().trim()
            if (name.isBlank()) {
                Toast.makeText(this, "Enter a category name", Toast.LENGTH_SHORT).show()
            } else {
                FakeRepository.addCategory(name)
                Toast.makeText(this, "Category saved", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}

