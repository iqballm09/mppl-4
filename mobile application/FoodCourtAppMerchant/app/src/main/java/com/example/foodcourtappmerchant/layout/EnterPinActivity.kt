package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.data.WithdrawRequest
import com.example.foodcourtappmerchant.data.WithdrawResponseParent
import com.example.foodcourtappmerchant.databinding.ActivityEnterPinBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnterPinActivity : AppCompatActivity() {
    lateinit var binding: ActivityEnterPinBinding
    private lateinit var mUserPreferences: UserPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEnterPinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.pin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mUserPreferences = UserPreferences(this)

        val amount = intent.getStringExtra(EXTRA_NOMINAL)
        val bank = intent.getStringExtra(EXTRA_BANK)
        val accountNumber = intent.getStringExtra(EXTRA_ACCOUNT)

        binding.btnProceed.setOnClickListener {
            val pinNumber = binding.edtPin.text.toString()
            if (pinNumber.length < 6) {
                Toast.makeText(this, "Please Enter Pin",Toast.LENGTH_SHORT).show()
            } else {
                val withdraw = WithdrawRequest(amount, bank, accountNumber, pinNumber)
                postWithdraw(withdraw, amount, accountNumber, bank)
            }
        }
    }

    private fun postWithdraw(withdraw: WithdrawRequest, amount: String?, accountNumber: String?, bank: String?) {
        showLoading(true)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.postWithdraw(mUserPreferences.getUser().token, withdraw).enqueue(
            object : Callback<WithdrawResponseParent> {
                override fun onResponse(
                    call: Call<WithdrawResponseParent>,
                    response: Response<WithdrawResponseParent>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Your money is sent", Toast.LENGTH_SHORT).show()
                        val moveWithDataIntent = Intent(this@EnterPinActivity, ConfirmationActivity::class.java)
                        moveWithDataIntent.putExtra(ConfirmationActivity.EXTRA_ACCOUNT, accountNumber)
                        moveWithDataIntent.putExtra(ConfirmationActivity.EXTRA_NOMINAL, amount)
                        moveWithDataIntent.putExtra(ConfirmationActivity.EXTRA_BANK, bank)
                        startActivity(moveWithDataIntent)
                    } else {
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                        Log.d("Payment", "onResponse: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<WithdrawResponseParent>, t: Throwable) {
                    showLoading(false)
                    Log.e("Payment", "onFailure: ${t.message}", )
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

    companion object {
        const val EXTRA_NOMINAL = "extra_nominal"
        const val EXTRA_BANK = "extra_bank"
        const val EXTRA_ACCOUNT = "extra_account"
    }
}