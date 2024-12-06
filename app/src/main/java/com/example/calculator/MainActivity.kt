package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentDisplay: String = ""
    private var operator: String? = null
    private var firstNumber: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val numberClickListener = { digit: String ->
            currentDisplay += digit
            binding.tvDisplay.text = currentDisplay
        }

        // Set listeners for number buttons
        setNumberButtonListeners(numberClickListener)

        // Operator button clicks
        binding.btnAdd.setOnClickListener {
            operatorClickListener("+")
        }
        binding.btnSubtract.setOnClickListener {
            operatorClickListener("-")
        }
        binding.btnMultiply.setOnClickListener {
            operatorClickListener("*") }
        binding.btnDivide.setOnClickListener {
            operatorClickListener("/") }

        // Equal button click
        binding.btnEqual.setOnClickListener {
            calculateResult()
        }

        // Clear button click
        binding.btnClear.setOnClickListener { clearCalculator() }
    }

    // Function to handle number button clicks
    private fun setNumberButtonListeners(numberClickListener: (String) -> Unit) {
        binding.btn0.setOnClickListener { numberClickListener("0") }
        binding.btn1.setOnClickListener { numberClickListener("1") }
        binding.btn2.setOnClickListener { numberClickListener("2") }
        binding.btn3.setOnClickListener { numberClickListener("3") }
        binding.btn4.setOnClickListener { numberClickListener("4") }
        binding.btn5.setOnClickListener { numberClickListener("5") }
        binding.btn6.setOnClickListener { numberClickListener("6") }
        binding.btn7.setOnClickListener { numberClickListener("7") }
        binding.btn8.setOnClickListener { numberClickListener("8") }
        binding.btn9.setOnClickListener { numberClickListener("9") }
    }

    // Function to handle operator clicks (+, -, *, /)
    private fun operatorClickListener(op: String) {
        firstNumber = currentDisplay.toDouble()
        operator = op
        currentDisplay = ""
    }

    // Function to calculate and display the result
    private fun calculateResult() {
        if (operator != null) {
            val secondNumber = currentDisplay.toDoubleOrNull() ?: return
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
                else -> 0.0
            }
            binding.tvDisplay.text = result.toString()
            currentDisplay = ""
            operator = null
        }
    }

    // Function to clear the calculator
    private fun clearCalculator() {
        currentDisplay = ""
        operator = null
        binding.tvDisplay.text = "0"
    }
}
