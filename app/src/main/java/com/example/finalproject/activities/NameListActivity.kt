package com.example.finalproject.activities


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.adapters.CusAdapter
import com.example.finalproject.models.CustomerModel
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class NameListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var rvCus: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var cusList: ArrayList<CustomerModel>
    private lateinit var dbRef:DatabaseReference
    private lateinit var searchView: SearchView

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_list)//set the layout activity_name_list for NameListActivity

        rvCus = findViewById(R.id.rvCus)
        rvCus.layoutManager = LinearLayoutManager(this)
        rvCus.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchView = findViewById(R.id.searchView)
        cusList =  arrayListOf<CustomerModel>()

        // set up the SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search your name here"

        cusList = arrayListOf<CustomerModel>()
        getCustomerData()
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!TextUtils.isEmpty(newText)) {
            search(newText!!)
        } else {
            getCustomerData()
        }
        return true
    }
    private fun search(query: String) {
        val searchResultList = arrayListOf<CustomerModel>()
        for (CustomerModel in cusList) {
            if (CustomerModel.cusName?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase()) == true) {
                searchResultList.add(CustomerModel)
            }
        }
        val mAdapter = CusAdapter(searchResultList)
        rvCus.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : CusAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent (this@NameListActivity,Show2Activity::class.java)
                //put extras
                intent.putExtra("cusId", searchResultList[position].cusId)
                intent.putExtra("cusName", searchResultList[position].cusName)
                intent.putExtra("cusPhone", searchResultList[position].cusPhone)
                intent.putExtra("cusMail", searchResultList[position].cusMail)
                intent.putExtra("cusAdd", searchResultList[position].cusAdd)

                startActivity(intent)
            }
        })
    }






    private fun getCustomerData(){
        rvCus.visibility = View.GONE
        tvLoadingData.visibility=View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference( "customers")//set path for the database
        dbRef.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                cusList.clear()
                if(snapshot.exists()){
                    for(cusSnap in snapshot.children){
                        val cusData = cusSnap.getValue(CustomerModel::class.java)
                        cusList.add(cusData!!)
                    }
                    val mAdapter = CusAdapter(cusList)
                    rvCus.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object :CusAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent (this@NameListActivity,Show2Activity::class.java)

                            intent.putExtra("cusId",cusList[position].cusId)
                            intent.putExtra("cusName",cusList[position].cusName)
                            intent.putExtra("cusPhone",cusList[position].cusPhone)
                            intent.putExtra("cusMail",cusList[position].cusMail)
                            intent.putExtra("cusAdd",cusList[position].cusAdd)
                            startActivity(intent)
                        }

                    })


                    rvCus.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}