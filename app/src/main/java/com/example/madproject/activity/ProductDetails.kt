package com.example.madproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.madproject.R
import com.example.madproject.models.ProductModel
import com.google.firebase.database.FirebaseDatabase

class ProductDetails : AppCompatActivity() {

    //initialize variables

    private lateinit var tvProductId: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvProductURL: TextView
    private lateinit var tvProductQuantity: TextView
    private lateinit var tvProductDiscription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var button8: Button
    private lateinit var button11: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        button8 = findViewById(R.id.button8)
        button11 = findViewById(R.id.button11)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("productId").toString() ,
                intent.getStringExtra("PersonName").toString()

            )
        }

    // set the delete button and delete notification

        btnDelete.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Item")
            builder.setMessage("Are you sure you want to delete this Product?")
            builder.setPositiveButton("Yes") { dialog, which ->
                deleteRecord(
                    intent.getStringExtra("productId").toString()
                )
            }
            builder.setNegativeButton("No", null)
            builder.show()
        }

// set for redirecting product calculate page

        button8.setOnClickListener {
            val intent = Intent( this, ProductCalculate::class.java)
            startActivity(intent)
        }

// set for redirecting product product category page

        button11.setOnClickListener {
            val intent = Intent( this,ProductCategory ::class.java)
            startActivity(intent)
        }






    }

    //  deleting products from database


    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val mTask = dbRef.removeValue()

        // show the alertBox before deleting product

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Product data deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this,FetchActivity::class.java)

            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
        }
    }






    private fun initView() {

        tvProductId = findViewById(R.id.tvProductId)
        tvProductName = findViewById(R.id.tvProductName)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        tvProductURL = findViewById(R.id.tvProductURL)
        tvProductQuantity = findViewById(R.id.tvProductQuantity)
        tvProductDiscription = findViewById(R.id.tvProductDiscription)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }


    private fun setValuesToViews(){
        tvProductId.text = intent.getStringExtra("productId")
        tvProductName.text = intent.getStringExtra("PersonName")
        tvProductPrice.text = intent.getStringExtra("Productprice")
        tvProductURL.text = intent.getStringExtra("ProductURL")
        tvProductQuantity.text = intent.getStringExtra("ProductQuantity")
        tvProductDiscription.text = intent.getStringExtra("ProductDiscription")



    }
//show the updating dialog box

    private fun openUpdateDialog(
        productId: String,
        PersonName: String
    ){

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_details,null)

        mDialog.setView(mDialogView)

//EditText set data in DialogBox

        val editTextTextPersonName = mDialogView.findViewById<EditText>(R.id.editTextTextPersonName)
        val editTextTextProductprice = mDialogView.findViewById<EditText>(R.id.editTextTextProductprice)
        val  editTextTextProductURL= mDialogView.findViewById<EditText>(R.id.editTextTextProductURL)
        val editTextTextProductQuantity = mDialogView.findViewById<EditText>(R.id.editTextTextProductQuantity)
        val editTextTextProductDiscription= mDialogView.findViewById<EditText>(R.id.editTextTextProductDiscription)
        val btnUpdateData= mDialogView.findViewById<Button>(R.id.btnUpdateData)


        editTextTextPersonName.setText(intent.getStringExtra("PersonName").toString())
        editTextTextProductprice.setText(intent.getStringExtra("Productprice").toString())
        editTextTextProductURL.setText(intent.getStringExtra("ProductURL").toString())
        editTextTextProductQuantity.setText(intent.getStringExtra("ProductQuantity").toString())
        editTextTextProductDiscription.setText(intent.getStringExtra("ProductDiscription").toString())

        mDialog.setTitle("Updating $PersonName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateProductData(
                productId,
                editTextTextPersonName.text.toString(),
                editTextTextProductprice.text.toString(),
                editTextTextProductURL.text.toString(),
                editTextTextProductQuantity.text.toString(),
                editTextTextProductDiscription.text.toString()
            )

            Toast.makeText(applicationContext,"Product Data Updated",Toast.LENGTH_LONG).show()

            //setting updated data to textViews

            tvProductName.text = editTextTextPersonName.text.toString()
            tvProductPrice.text = editTextTextProductprice.text.toString()
            tvProductURL.text = editTextTextProductURL.text.toString()
            tvProductQuantity.text = editTextTextProductQuantity.text.toString()
            tvProductDiscription.text = editTextTextProductDiscription.text.toString()

            alertDialog.dismiss()
        }


    }
    // set updating details in database
        private fun updateProductData(
            id:String,
            name:String,
            price:String,
            url:String,
            quantity:String,
            discription:String
        ){
            val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
            val productInfo = ProductModel(id,name,price,url,quantity,discription)
            dbRef.setValue(productInfo)
        }



}