package com.example.balancewise

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balancewise.data.FakeRepository

class GoalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val seekMin = findViewById<SeekBar>(R.id.seekMin)
        val seekMax = findViewById<SeekBar>(R.id.seekMax)
        val tvMinValue = findViewById<TextView>(R.id.tvMinValue)
        val tvMaxValue = findViewById<TextView>(R.id.tvMaxValue)
        val btnSaveGoals = findViewById<Button>(R.id.btnSaveGoals)

        seekMin.progress = FakeRepository.minGoal
        seekMax.progress = FakeRepository.maxGoal
        tvMinValue.text = "R ${seekMin.progress}"
        tvMaxValue.text = "R ${seekMax.progress}"

        seekMin.setOnSeekBarChangeListener(simpleListener { tvMinValue.text = "R $it" })
        seekMax.setOnSeekBarChangeListener(simpleListener { tvMaxValue.text = "R $it" })

        btnSaveGoals.setOnClickListener {
            FakeRepository.minGoal = seekMin.progress
            FakeRepository.maxGoal = seekMax.progress
            Toast.makeText(this, "Goals updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun simpleListener(onChange: (Int) -> Unit): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) = onChange(progress)
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }
    }
}
