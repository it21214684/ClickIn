package com.example.madproject.activity

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
import com.example.madproject.R
import com.example.madproject.adapters.ProductAdapter
import com.example.madproject.models.ProductModel
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class FetchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    //initialize variables

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var searchView: SearchView
    private lateinit var productList: ArrayList<ProductModel>
    private lateinit var  dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")

  // show the loading  the product data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        productRecyclerView = findViewById(R.id.rvEmp)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        searchView = findViewById(R.id.searchView)
        productList = arrayListOf<ProductModel>()

        // set up the SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search Product"

        getProductData()

    }
// search the product using search Box

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!TextUtils.isEmpty(newText)) {
            search(newText!!)
        } else {
            getProductData()
        }
        return true
    }
    private fun search(query: String) {
        val searchResultList = arrayListOf<ProductModel>()
        for (EmployeeModel in productList) {
            if (EmployeeModel.PersonName?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase()) == true) {
                searchResultList.add(EmployeeModel)
            }
        }
        val mAdapter = ProductAdapter(searchResultList)
        productRecyclerView.adapter = mAdapter

        mAdapter.setonItemClickListener(object :ProductAdapter.onItemClickListener{
            override fun onItemClickListener(position: Int) {
                val intent = Intent(this@FetchActivity,ProductDetails::class.java)
                //put extras
                intent.putExtra("productId",productList[position].productId)
                intent.putExtra("PersonName",productList[position].PersonName)
                intent.putExtra("Productprice",productList[position].Productprice)
                intent.putExtra("ProductURL",productList[position].ProductURL)
                intent.putExtra("ProductQuantity",productList[position].ProductQuantity)
                intent.putExtra("ProductDiscription",productList[position].ProductDiscription)
                startActivity(intent)
            }
        })
    }

    // retrieve the  product data
    private fun getProductData(){
        productRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

         dbRef = FirebaseDatabase.getInstance().getReference("Products")

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if(snapshot.exists()){
                    for(productSnap in snapshot.children){
                        val productData = productSnap.getValue(ProductModel::class.java)
                        productList.add(productData!!)
                    }
                    val mAdapter = ProductAdapter(productList)
                    productRecyclerView.adapter = mAdapter

                    mAdapter.setonItemClickListener(object :ProductAdapter.onItemClickListener{
                        override fun onItemClickListener(position: Int) {
                           val intent = Intent(this@FetchActivity,ProductDetails::class.java)


                            intent.putExtra("productId",productList[position].productId)
                            intent.putExtra("PersonName",productList[position].PersonName)
                            intent.putExtra("Productprice",productList[position].Productprice)
                            intent.putExtra("ProductURL",productList[position].ProductURL)
                            intent.putExtra("ProductQuantity",productList[position].ProductQuantity)
                            intent.putExtra("ProductDiscription",productList[position].ProductDiscription)
                            startActivity(intent)
                        }

                    })

                    productRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}