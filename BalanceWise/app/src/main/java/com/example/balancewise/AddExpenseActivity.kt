package com.example.balancewise

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.balancewise.data.FakeRepository
import com.example.balancewise.model.ExpenseItem
import java.util.Calendar

class AddExpenseActivity : AppCompatActivity() {
    private var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnDate = findViewById<Button>(R.id.btnDate)
        val btnStartTime = findViewById<Button>(R.id.btnStartTime)
        val btnEndTime = findViewById<Button>(R.id.btnEndTime)
        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategory)
        val btnPhoto = findViewById<Button>(R.id.btnPhoto)
        val imgReceiptPreview = findViewById<ImageView>(R.id.imgReceiptPreview)
        val btnSaveExpense = findViewById<Button>(R.id.btnSaveExpense)

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, FakeRepository.categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = categoryAdapter

        val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedPhotoUri = uri
            if (uri != null) imgReceiptPreview.setImageURI(uri)
        }

        btnPhoto.setOnClickListener { imagePicker.launch("image/*") }

        btnDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(this, { _, y, m, d ->
                btnDate.text = "%02d/%02d/%04d".format(d, m + 1, y)
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnStartTime.setOnClickListener { showTimePicker(btnStartTime) }
        btnEndTime.setOnClickListener { showTimePicker(btnEndTime) }

        btnSaveExpense.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val date = btnDate.text.toString()
            val startTime = btnStartTime.text.toString()
            val endTime = btnEndTime.text.toString()
            val category = spinnerCategory.selectedItem?.toString() ?: ""

            if (amountText.isBlank() || description.isBlank() || date == "Select Date" ||
                startTime == "Start Time" || endTime == "End Time") {
                Toast.makeText(this, "Complete all required fields", Toast.LENGTH_SHORT).show()
            } else {
                FakeRepository.addExpense(
                    ExpenseItem(
                        amount = amountText.toDouble(),
                        date = date,
                        startTime = startTime,
                        endTime = endTime,
                        description = description,
                        category = category,
                        photoUri = selectedPhotoUri?.toString()
                    )
                )
                Toast.makeText(this, "Expense saved", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun showTimePicker(target: Button) {
        val cal = Calendar.getInstance()
        TimePickerDialog(this, { _, h, m ->
            target.text = "%02d:%02d".format(h, m)
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }
}
