package com.example.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.finalproject.R
import com.example.finalproject.models.CustomerModel
import com.google.firebase.database.FirebaseDatabase

class Show2Activity : AppCompatActivity() {
    private lateinit var cusId:TextView
    private lateinit var tvCusName:TextView
    private lateinit var tvCusPhone:TextView
    private lateinit var tvCusMail:TextView
    private lateinit var tvCusAdd:TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button
    private lateinit var btnBack:Button
    private lateinit var btnDash:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show2)//set the layout activity_show2 for Show2Activity

        btnBack = findViewById(R.id.btnBack) //retrieving "btnBack" and storing a reference
        btnDash = findViewById(R.id.btnDash) //retrieving "btnDash" and storing a reference

        initView()//invoking initView function
        setValuesToViews()//invoking setValuesToViews function

        //set a click listener for a "btnUpdate"
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cusId").toString(),
                intent.getStringExtra("cusName").toString()
            )
        }
        //set a click listener for a "btnDelete"
        btnDelete.setOnClickListener {


            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Item")
            builder.setMessage("Are you sure you want to delete this item?")
            builder.setPositiveButton("Yes") { dialog, which ->
                deleteRecord(
                    intent.getStringExtra("cusId").toString()
                )
            }
            builder.setNegativeButton("No", null)
            builder.show()


        }






        //set a click listener for a "btnBack"
        btnBack.setOnClickListener {

            val intent = Intent( this, NameListActivity::class.java)
            startActivity(intent)

        }
//set a click listener for a "btnDash"
        btnDash.setOnClickListener {

            val intent = Intent( this, MainActivity::class.java)
            startActivity(intent)

        }

    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("customers").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Customer data deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this,NameListActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
        }
    }




    private fun initView(){
        cusId = findViewById(R.id.cusId)
        tvCusName = findViewById(R.id.tvCusName)
        tvCusPhone = findViewById(R.id.tvCusPhone)
        tvCusMail = findViewById(R.id.tvCusMail)
        tvCusAdd = findViewById(R.id.tvCusAdd)
        btnUpdate = findViewById(R.id. btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){
        cusId.text = intent.getStringExtra("cusId")
        tvCusName.text = intent.getStringExtra("cusName")
        tvCusPhone.text = intent.getStringExtra("cusPhone")
        tvCusMail.text = intent.getStringExtra("cusMail")
        tvCusAdd.text = intent.getStringExtra("cusAdd")

    }

    private fun openUpdateDialog(
        cusId: String,
        cusName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog,null)

        mDialog.setView(mDialogView)

        val editTextTextPersonName = mDialogView.findViewById<EditText>(R.id.editTextTextPersonName)
        val editTextTextPhone = mDialogView.findViewById<EditText>(R.id.editTextTextPhone)
        val editTextTextEmail = mDialogView.findViewById<EditText>(R.id.editTextTextEmail)
        val editTextTextAddress = mDialogView.findViewById<EditText>(R.id.editTextTextAddress)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        editTextTextPersonName.setText(intent.getStringExtra("cusName").toString())
        editTextTextPhone.setText(intent.getStringExtra("cusPhone").toString())
        editTextTextEmail.setText(intent.getStringExtra("cusMail").toString())
        editTextTextAddress.setText(intent.getStringExtra("cusAdd").toString())


        mDialog.setTitle("Updating $cusName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateCusData(
                cusId,
                editTextTextPersonName.text.toString(),
                editTextTextPhone.text.toString(),
                editTextTextEmail.text.toString(),
                editTextTextAddress.text.toString()
            )
            Toast.makeText(applicationContext,"Customer data updated",Toast.LENGTH_LONG).show()
            // setting updated data to our textviews
            tvCusName.text = editTextTextPersonName.text.toString()
            tvCusPhone.text = editTextTextPhone.text.toString()
            tvCusMail.text = editTextTextEmail.text.toString()
            tvCusAdd.text = editTextTextAddress.text.toString()
            alertDialog.dismiss()
        }


    }

    private fun updateCusData(
        id:String,
        name:String,
        phone:String,
        email:String,
        address:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("customers").child(id)
        val cusInfo = CustomerModel(id,name,phone,email,address)
        dbRef.setValue(cusInfo)



    }



}