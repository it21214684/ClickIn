package com.example.clickin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.clickin.R

class Details : AppCompatActivity() {

    private lateinit var button8: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vacanciess_details)

        button8 = findViewById(R.id.button8)


        button8.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }

    }
}