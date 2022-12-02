package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.data.UserResponse
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WithdrawActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawBinding
    private lateinit var mUserPreferences: UserPreferences
    var saldo: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.withdraw)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mUserPreferences = UserPreferences(this)
        setData()

        binding.btnWithdraw.setOnClickListener {
            val amount = binding.edtValue.text.toString()
            if (binding.edtValue.text.isNotEmpty() && binding.edtBankAccount.text.isNotEmpty()) {
                if (amount.toInt() > (saldo.toInt()) - 1000) {
                    Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show()
                } else {
                    val moveWithDataIntent =
                        Intent(this@WithdrawActivity, WithdrawReceiptActivity::class.java)
                    moveWithDataIntent.putExtra(
                        WithdrawReceiptActivity.EXTRA_NOMINAL,
                        binding.edtValue.text.toString()
                    )
                    moveWithDataIntent.putExtra(
                        WithdrawReceiptActivity.EXTRA_BANK,
                        binding.spinnerBank.selectedItem.toString()
                    )
                    moveWithDataIntent.putExtra(
                        WithdrawReceiptActivity.EXTRA_ACCOUNT,
                        binding.edtBankAccount.text.toString()
                    )
                    startActivity(moveWithDataIntent)
                }
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        val bankList =
            arrayOf("BNI", "BRI", "Bank Mandiri", "BCA", "Bank Mega", "ATM Bersama", "Bank Permata")

        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bankList)

        binding.spinnerBank.adapter = arrayAdapter
        binding.spinnerBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun setData() {
        showLoading(true)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getCard(mUserPreferences.getUser().token).enqueue(
            object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        showLoading(false)
                        saldo = response.body()!!.merchant.income.toString()
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}