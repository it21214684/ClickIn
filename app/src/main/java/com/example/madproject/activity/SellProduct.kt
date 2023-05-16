package com.example.madproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.madproject.R
import com.example.madproject.models.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SellProduct : AppCompatActivity() {

//initialize variables

    private lateinit var editTextTextPersonName:EditText
    private lateinit var editTextTextProductprice:EditText
    private lateinit var editTextTextProductURL:EditText
    private lateinit var editTextTextProductQuantity:EditText
    private lateinit var editTextTextProductDiscription:EditText
    private lateinit var button4:Button

    // Setting data in firebase Database
    private lateinit var dbRef:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_product)

        // getting data from edit text

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextTextProductprice = findViewById(R.id.editTextTextProductprice)
        editTextTextProductURL = findViewById(R.id.editTextTextProductURL)
        editTextTextProductQuantity = findViewById(R.id.editTextTextProductQuantity)
        editTextTextProductDiscription = findViewById(R.id.editTextTextProductDiscription)
        button4 = findViewById(R.id.button4)

// Setting data in firebase Database

        dbRef = FirebaseDatabase.getInstance().getReference("Products")

        button4.setOnClickListener{
            saveProductData()
        }

    }

    private fun saveProductData(){

  // this codes are executed after click

        val PersonName = editTextTextPersonName.text.toString()
        val Productprice = editTextTextProductprice.text.toString()
        val ProductURL = editTextTextProductURL.text.toString()
        val ProductQuantity = editTextTextProductQuantity.text.toString()
        val ProductDiscription = editTextTextProductDiscription.text.toString()

  // Form  Validations

        if(PersonName.isEmpty()||Productprice.isEmpty()||ProductURL.isEmpty()||ProductQuantity.isEmpty()||ProductDiscription.isEmpty()){

        if(PersonName.isEmpty()){
            editTextTextPersonName.error = "Product Name is required"
        }
        if(Productprice.isEmpty()){
            editTextTextProductprice.error = "Product Price is required"
        }
        if(ProductURL.isEmpty()){
            editTextTextProductURL.error = "Product URL is required"
        }
        if(ProductQuantity.isEmpty()){
            editTextTextProductQuantity.error = "Product Quantity is required"
        }
        if(ProductDiscription.isEmpty()){
            editTextTextProductDiscription.error = "Product Discription is required"
        }
        }
        else{

        val productId = dbRef.push().key!!

//  on below line passing all data to model class

        val product = ProductModel(productId,PersonName,Productprice,ProductURL,ProductQuantity,ProductDiscription)

        dbRef.child(productId).setValue(product)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted sucessfully",Toast.LENGTH_LONG).show()

                editTextTextPersonName.text.clear()
                editTextTextProductprice.text.clear()
                editTextTextProductURL.text.clear()
                editTextTextProductQuantity.text.clear()
                editTextTextProductDiscription.text.clear()

   // display a toast message

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error${err.message}",Toast.LENGTH_LONG).show()

            }
    }
}}