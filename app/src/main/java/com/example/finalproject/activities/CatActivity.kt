package com.example.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.finalproject.R

class CatActivity : AppCompatActivity() {
    private lateinit var button100: Button
    private lateinit var button101: Button
    private lateinit var button81: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)//set the layout activity_cat for CatActivity

        button100 = findViewById(R.id.button100)//retrieving "button100" and storing a reference
        button101 = findViewById(R.id.button101)//retrieving "button101" and storing a reference
        button81 = findViewById(R.id.button81)//retrieving "button81" and storing a reference

        //set a click listener for a "button100"
        button100.setOnClickListener{

            val intent = Intent( this, BuyActivity2::class.java)
            startActivity(intent)

        }
        //set a click listener for a "button101"
        button101.setOnClickListener{

            val intent = Intent( this, BuyActivity2::class.java)
            startActivity(intent)

        }
        //set a click listener for a "button81"
        button81.setOnClickListener{

            val intent = Intent( this, BuyActivity2::class.java)
            startActivity(intent)

        }
    }
}