package com.example.clickin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.clickin.R

class VacanciessActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var buttond: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vacanciess)

        button = findViewById(R.id.button)
        buttond = findViewById(R.id.buttond)

        button.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }
        buttond.setOnClickListener {
            val intent = Intent(this, Details::class.java)
            startActivity(intent)
        }

    }
}