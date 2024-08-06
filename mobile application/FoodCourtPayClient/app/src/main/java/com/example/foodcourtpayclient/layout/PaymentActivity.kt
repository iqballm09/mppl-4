package com.example.foodcourtpayclient.layout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.UserResponse
import com.example.foodcourtpayclient.databinding.ActivityPaymentBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.temporal.TemporalAmount

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var mUserPreferences: UserPreferences
    private var pay: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        mUserPreferences = UserPreferences(this)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.payment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSaldo()
        val merchant_id = intent.getStringExtra(EXTRA_MERCHANT)
        binding.btnProceed.setOnClickListener {
            val amount = binding.edtAmount.text.toString().toInt()
            Log.d("Amount", "onCreate: $amount")
            checkBalance(amount, pay, merchant_id)
        }
    }

    private fun checkBalance(amount: Int, saldo: Int, merchantID: String?) {
        if (amount > saldo) {
            Toast.makeText(this, "Insufficient Funds", Toast.LENGTH_SHORT).show()
        } else {
            val moveWithDataIntent = Intent(this@PaymentActivity, PaymentActivity2::class.java)
            moveWithDataIntent.putExtra(PaymentActivity2.EXTRA_PAYMENT, amount.toString())
            moveWithDataIntent.putExtra(PaymentActivity2.EXTRA_MERCHANT, merchantID)
            startActivity(moveWithDataIntent)
        }
    }

    private fun setSaldo() {
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getCard(mUserPreferences.getUser().token).enqueue(
            object: Callback<UserResponse> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding.cardName.text = response.body()!!.card.name
                        binding.saldo.text = " " + (response.body()!!.card.saldo)
                        pay = response.body()!!.card.saldo!!
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

    companion object {
        const val EXTRA_MERCHANT = "extra_merchant"
    }
}