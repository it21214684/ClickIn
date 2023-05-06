package com.example.clickin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.clickin.R

class DashActivity : AppCompatActivity(){

    private lateinit var button12: Button
    private lateinit var button14: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        button12 = findViewById(R.id.button12)
        button14 = findViewById(R.id.button14)

        button12.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        button14.setOnClickListener {
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }

    }


}