package com.example.foodcourtpayclient.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtpayclient.ListTransactionAdapter
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.ListTransactionResponse
import com.example.foodcourtpayclient.databinding.ActivityHistoryTransactionBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryTransactionBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryTransactionBinding.inflate(layoutInflater)
        mUserPreferences = UserPreferences(this)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.transaction_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTransactionList()
    }

    private fun setTransactionList() {
        showLoading(true)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_transaction)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getTransaction(mUserPreferences.getUser().token).enqueue(
            object : Callback<ListTransactionResponse> {
                override fun onResponse(
                    call: Call<ListTransactionResponse>,
                    response: Response<ListTransactionResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("List Success", response.body().toString())
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@HistoryTransactionActivity)
                            adapter = ListTransactionAdapter(response.body()!!.payments.asReversed())
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}