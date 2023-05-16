package com.example.madproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.madproject.R

class ProductCategory : AppCompatActivity() {

    private lateinit var button88: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category)

        button88 = findViewById(R.id.button88)

        button88.setOnClickListener {
            val intent = Intent( this,Info ::class.java)
            startActivity(intent)
        }
    }
}