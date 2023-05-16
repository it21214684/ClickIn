package com.example.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.finalproject.R


class cusView : AppCompatActivity() {
    private  lateinit var input1 :EditText
    private   lateinit var input2 :EditText
    private  lateinit  var buttonCal :Button
    private lateinit var button17: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus_view)//set the layout activity_cus_view for cusView

        input1 = findViewById(R.id.editTextTextEmailAddress3)
        input2 = findViewById(R.id.editTextTextEmailAddress2)
        buttonCal = findViewById(R.id.button4)//retrieving "button4" and storing a reference
        button17 = findViewById(R.id.button17)//retrieving "button17" and storing a reference

        buttonCal.setOnClickListener {
            // Get the values from the input fields
            val value1 = input1.text.toString().toDouble()
            val value2 = input2.text.toString().toDouble()

            // Multiply the values
            val result = value1 * value2

            // Display the result in a TextView
            val resultTextView = findViewById<TextView>(R.id.editTextTextEmailAddress4)
            resultTextView.text = result.toString()

            if (value1 > 100) {//condition
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Km limit Exceeded")//title of the alert message
                    .setMessage("Your extra charger is 250")//alert message
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()

                alertDialog.show()//display alert
            }



        }
        //set a click listener for a "button17"
        button17.setOnClickListener{

            val intent = Intent( this, BuyActivity2::class.java)
            startActivity(intent)

        }
    }



}