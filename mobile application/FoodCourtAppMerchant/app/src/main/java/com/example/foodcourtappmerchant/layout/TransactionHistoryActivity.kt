package com.example.foodcourtappmerchant.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.adapter.ListTransactionAdapter
import com.example.foodcourtappmerchant.data.ListTransactionResponse
import com.example.foodcourtappmerchant.databinding.ActivityTransactionHistoryBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionHistoryBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.transaction_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mUserPreferences = UserPreferences(this)
        setTransactionList()
    }

    private fun setTransactionList() {
        showLoading(true)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_transaction)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getTransactions(mUserPreferences.getUser().token).enqueue(
            object : Callback<ListTransactionResponse> {
                override fun onResponse(
                    call: Call<ListTransactionResponse>,
                    response: Response<ListTransactionResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("List Success", response.body().toString())
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@TransactionHistoryActivity)
                            adapter = ListTransactionAdapter( response.body()!!.payments.asReversed())
                        }
                        showLoading(false)
                    }
                }

                override fun onFailure(call: Call<ListTransactionResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("Failure :", t.message!!)
                }
            }
        )
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}