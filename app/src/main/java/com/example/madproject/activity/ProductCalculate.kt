package com.example.madproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.madproject.R
// calculate the products
class ProductCalculate : AppCompatActivity() {
    private  lateinit var input1 : EditText
    private   lateinit var input2 :EditText
    private  lateinit  var buttonCal:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_calculate)

        input1 = findViewById(R.id.p1)
        input2 = findViewById(R.id.p2)
        buttonCal = findViewById(R.id.button100)

        buttonCal.setOnClickListener {
            // Get the values from the input fields
            val value1 = input1.text.toString().toDouble()
            val value2 = input2.text.toString().toDouble()

            // Multiply the values
            val result = value1 * value2

            // Display the result in a TextView
            val resultTextView = findViewById<TextView>(R.id.p3)
            resultTextView.text = result.toString()
        }





    }
}