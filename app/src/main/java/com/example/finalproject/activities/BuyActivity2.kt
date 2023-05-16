package com.example.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.finalproject.R
import com.example.finalproject.models.CustomerModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class BuyActivity2 : AppCompatActivity() {

    private lateinit var editTextTextPersonName: EditText
    private lateinit var editTextTextPhone: EditText
    private lateinit var editTextTextEmail: EditText
    private lateinit var editTextTextAddress: EditText

    private lateinit var button76 : Button
    private lateinit var button15: Button
    private lateinit var button10: Button
    private lateinit var button16: Button

    private lateinit var dbRef: DatabaseReference







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy2)//set the layout activity_buy2 for BuyActivity2

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextTextPhone = findViewById(R.id.editTextTextPhone)
        editTextTextEmail = findViewById(R.id.editTextTextEmail)
        editTextTextAddress = findViewById(R.id.editTextTextAddress)

        button76 = findViewById(R.id.button76)//retrieving "button76" and storing a reference
        button15 = findViewById(R.id.button15)
        button10 = findViewById(R.id.button10)//retrieving "button10" and storing a reference
        button16 = findViewById(R.id.button16)

        dbRef = FirebaseDatabase.getInstance().getReference("customers")//set path for the database

        button76.setOnClickListener {

            confirmCustomerData()

        }

        //set a click listener for a "button15"
        button15.setOnClickListener{

            val intent = Intent( this, cusView::class.java)
            startActivity(intent)

        }
        //set a click listener for a "button10"
        button10.setOnClickListener{

            val intent = Intent( this, NameListActivity::class.java)
            startActivity(intent)

        }
        //set a click listener for a "button16"
        button16.setOnClickListener {

            val intent = Intent( this, CatActivity::class.java)
            startActivity(intent)

        }
    }



    private fun confirmCustomerData() {
        //getting values
        val cusName = editTextTextPersonName.text.toString()
        val cusPhone = editTextTextPhone.text.toString()
        val cusMail = editTextTextEmail.text.toString()
        val cusAdd = editTextTextAddress.text.toString()

        if(cusName.isEmpty()) {
            editTextTextPersonName.error = "Please enter your name"
        }
        if(cusPhone.isEmpty()) {
            editTextTextPhone.error = "Please enter your phone number"
        }
        if(cusMail.isEmpty()) {
            editTextTextEmail.error = "Please your email"
        }
        if(cusAdd.isEmpty()) {
            editTextTextAddress.error = "Please enter address"
        }

        val cusId = dbRef.push().key!!

        val customer = CustomerModel(cusId,cusName,cusPhone,cusMail,cusAdd)

        dbRef.child(cusId).setValue(customer)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG)

                editTextTextPersonName.text.clear()
                editTextTextPhone.text.clear()
                editTextTextEmail.text.clear()
                editTextTextAddress.text.clear()



            //validate to show empty columns
            }.addOnFailureListener { err->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }



}