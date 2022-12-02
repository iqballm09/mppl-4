package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.data.UserResponse
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawMenuBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WithdrawMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithdrawMenuBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserPreferences = UserPreferences(this)
        supportActionBar?.setTitle(R.string.withdraw)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setData()

        binding.btnBank.setOnClickListener {
            val intent = Intent(this, WithdrawActivity::class.java)
            startActivity(intent)
        }

        binding.tvHistory.setOnClickListener {
            val intent = Intent(this, WithdrawHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setData() {
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getCard(mUserPreferences.getUser().token).enqueue(
            object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding.saldo.text = " " + response.body()!!.merchant.income.toString()
                        binding.cardName.text = response.body()!!.merchant.name.toString()
                    } else {
                        Toast.makeText(applicationContext, "Unable to load data", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("Data", "onFailure: ${t.message}")
                }
            }
        )
    }
}