package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.UserResponse
import com.example.foodcourtpayclient.databinding.ActivityPayment2Binding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityPayment2Binding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayment2Binding.inflate(layoutInflater)
        mUserPreferences = UserPreferences(this)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.billing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSaldo()

        val payment = intent.getStringExtra(EXTRA_PAYMENT)
        val merchant_id = intent.getStringExtra(EXTRA_MERCHANT)
        Log.d("Sum Order", "onCreate: $payment")
        binding.tvOrderValue.text = " $payment."
        binding.tvOrderValueSum.text = " $payment"

        binding.btnProceed.setOnClickListener {
            val moveWithDataIntent = Intent(this@PaymentActivity2, PaymentPinActivity::class.java)
            moveWithDataIntent.putExtra(PaymentPinActivity.EXTRA_MERCHANT, merchant_id)
            moveWithDataIntent.putExtra(PaymentPinActivity.EXTRA_AMOUNT, payment)
            Log.d("AMOUNT", "onCreate: $payment")
            startActivity(moveWithDataIntent)
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

    companion object {
        const val EXTRA_PAYMENT = "extra_payment"
        const val EXTRA_MERCHANT = "extra_merchant"
    }
}