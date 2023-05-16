package com.example.clickin.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.clickin.models.EmployeeModel
import com.example.clickin.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etEmpAge: EditText
    private lateinit var etEmpAddress: EditText
    private lateinit var etEmpSkills: EditText
    private lateinit var etEmpEmail: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etEmpName = findViewById(R.id.etEmpName)
        etEmpAge = findViewById(R.id.etEmpAge)
        etEmpAddress = findViewById(R.id.etEmpAddress)
        etEmpSkills = findViewById(R.id.etEmpSkills)
        etEmpEmail = findViewById(R.id.etEmpEmail)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener {


//                val alertDialog = AlertDialog.Builder(this)
//                    .setTitle("Hey joob seeker")
//                    .setMessage("Check your emails. We will reply soon")
//                    .setPositiveButton("OK") { dialog, _ ->
//                        dialog.dismiss()
//                    }
//                    .create()
//
//                alertDialog.show()


            saveEmployeeData()





        }
    }

    private fun saveEmployeeData() {

        //getting values
        val empName = etEmpName.text.toString()
        val empAge = etEmpAge.text.toString()
        val empAddress = etEmpAddress.text.toString()
        val empSkills = etEmpSkills.text.toString()
        val empEmail = etEmpEmail.text.toString()
        //validation
        if(empName.isEmpty() || empAge.isEmpty() || empAddress.isEmpty() || empSkills.isEmpty() || empEmail.isEmpty()){
            if (empName.isEmpty()) {
            etEmpName.error = "Please enter name"
        }
        if (empAge.isEmpty()) {
            etEmpAge.error = "Please enter age"
        }
        if (empAddress.isEmpty()) {
            etEmpAddress.error = "Please enter salary"
        }
        if (empSkills.isEmpty()) {
            etEmpSkills.error = "Please enter skills"
        }
        if (empEmail.isEmpty()) {
            etEmpEmail.error = "Please enter email"
        }}
        else{
        val empId = dbRef.push().key!!

        val employee = EmployeeModel(empId, empName, empAge, empAddress, empSkills, empEmail)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etEmpName.text.clear()
                etEmpAge.text.clear()
                etEmpAddress.text.clear()
                etEmpSkills.text.clear()
                etEmpEmail.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }}

}