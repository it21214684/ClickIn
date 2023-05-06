package com.example.clickin.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clickin.adapters.EmpAdapter
import com.example.clickin.models.EmployeeModel
import com.example.clickin.R
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class FetchingActivity : AppCompatActivity(),SearchView.OnQueryTextListener{

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var searchView: SearchView
    private lateinit var empList: ArrayList<EmployeeModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchView = findViewById(R.id.searchView)

        empList = arrayListOf<EmployeeModel>()

        // set up the SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search Job Seekers"

        //get data from fire base
        getEmployeesData()

    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!TextUtils.isEmpty(newText)) {
            search(newText!!)
        } else {
            getEmployeesData()
        }
        return true
    }
    private fun search(query: String) {
        val searchResultList = arrayListOf<EmployeeModel>()
        for (EmployeeModel in empList) {
            if (EmployeeModel.empName?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase()) == true) {
                searchResultList.add(EmployeeModel)
            }
        }
        val mAdapter = EmpAdapter(searchResultList)
        empRecyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : EmpAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@FetchingActivity, EmployeeDetailsActivity::class.java)
                //put extras
                intent.putExtra("empId", searchResultList[position].empId)
                intent.putExtra("empName", searchResultList[position].empName)
                intent.putExtra("empAge", searchResultList[position].empAge)
                intent.putExtra("empAddress", searchResultList[position].empAddress)
                intent.putExtra("empSkills", searchResultList[position].empSkills)
                intent.putExtra("empEmail", searchResultList[position].empEmail)
                startActivity(intent)
            }
        })
    }
    private fun getEmployeesData() {

        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children) {
                        val empData = empSnap.getValue(EmployeeModel::class.java)
                        empList.add(empData!!)
                    }
                    val mAdapter = EmpAdapter(empList)
                    empRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : EmpAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent =
                                Intent(this@FetchingActivity, EmployeeDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("empId", empList[position].empId)
                            intent.putExtra("empName", empList[position].empName)
                            intent.putExtra("empAge", empList[position].empAge)
                            intent.putExtra("empAddress", empList[position].empAddress)
                            intent.putExtra("empSkills", empList[position].empSkills)
                            intent.putExtra("empEmail", empList[position].empEmail)
                            startActivity(intent)
                        }

                    })
                }else {
                    tvLoadingData.text = "no"
                }
                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                tvLoadingData.text = "Error: ${error.message}"
                tvLoadingData.visibility = View.VISIBLE
                empRecyclerView.visibility = View.GONE
            }

        })
    }
}