package com.example.foodcourtappmerchant.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.adapter.ListWithdrawsAdapter
import com.example.foodcourtappmerchant.data.ListWithdrawsRespose
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawHistoryBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WithdrawHistoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawHistoryBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mUserPreferences = UserPreferences(this)
        supportActionBar?.setTitle(R.string.withdraws_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setWithdrawsList()
    }

    private fun setWithdrawsList() {
        showLoading(true)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_withdraws)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getWithdraws(mUserPreferences.getUser().token).enqueue(
            object : Callback<ListWithdrawsRespose> {
                override fun onResponse(
                    call: Call<ListWithdrawsRespose>,
                    response: Response<ListWithdrawsRespose>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("List Success", response.body().toString())
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@WithdrawHistoryActivity)
                            adapter = ListWithdrawsAdapter( response.body()!!.withdraws.asReversed())
                        }
                        showLoading(false)
                    }
                }

                override fun onFailure(call: Call<ListWithdrawsRespose>, t: Throwable) {
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