package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.TransactionRequest
import com.example.foodcourtpayclient.data.TransactionResponse
import com.example.foodcourtpayclient.databinding.ActivityPaymentPinBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentPinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentPinBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentPinBinding.inflate(layoutInflater)
        mUserPreferences = UserPreferences(this)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.pin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val payment = intent.getStringExtra(EXTRA_AMOUNT)
        Log.d("pay amount", "onCreate: $payment")
        val merchant_id = intent.getStringExtra(EXTRA_MERCHANT)

        binding.btnProceed.setOnClickListener {
            val pinNumber = binding.edtPin.text.toString()
            if (binding.edtPin.length() < 6) {
                Toast.makeText(this, "Please Enter Pin",Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Transaction", "onCreate: $payment, $merchant_id")
                val transaction = TransactionRequest(payment, merchant_id, pinNumber)
                postTransaction(transaction)
            }
        }
    }

    private fun postTransaction(transaction: TransactionRequest) {
        showLoading(true)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.postTransaction(mUserPreferences.getUser().token, transaction).enqueue(
            object : Callback<TransactionResponse> {
                override fun onResponse(
                    call: Call<TransactionResponse>,
                    response: Response<TransactionResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Thank You for Purchasing", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, ConfirmationActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                        Log.d("Payment", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                    showLoading(false)
                    Log.e("Payment", "onFailure: ${t.message}", )
                }

            }
        )
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    companion object {
        const val EXTRA_AMOUNT = "extra_amount"
        const val EXTRA_MERCHANT = "extra_merchant"
    }
}