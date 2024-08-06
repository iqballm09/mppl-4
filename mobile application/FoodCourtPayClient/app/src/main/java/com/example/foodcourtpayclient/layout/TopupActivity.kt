package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.UserResponse
import com.example.foodcourtpayclient.databinding.ActivityTopupBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopupActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTopupBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopupBinding.inflate(layoutInflater)
        mUserPreferences = UserPreferences(this)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.topup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setSaldo()

        binding.tvHistory.setOnClickListener {
            val intent = Intent(this, HistoryTransactionActivity::class.java)
            startActivity(intent)
        }

        binding.btnBank.setOnClickListener {
            val intent = Intent(this, BankPaymentActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setSaldo() {
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getCard(mUserPreferences.getUser().token).enqueue(
            object: Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding.cardName.text = response.body()!!.card.name
                        binding.saldo.text = " " + response.body()!!.card.saldo.toString()
                        Log.d("Card Name", "onResponse: ${response.body()!!.card.name}")
                        Log.d("Saldo", "onResponse: ${response.body()!!.card.saldo}")
                    } else {
                        Toast.makeText(applicationContext, "Unable to load Saldo", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("Saldo", "onFailure: ${t.message}", )
                }
            }
        )
    }
}