package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.data.UserResponse
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawReceiptBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WithdrawReceiptActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawReceiptBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.withdraw)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val amount = intent.getStringExtra(EXTRA_NOMINAL)
        val bank = intent.getStringExtra(EXTRA_BANK)
        val accountNumber = intent.getStringExtra(EXTRA_ACCOUNT)

        binding.tvBankName.text = bank
        binding.tvBankAccount.text = accountNumber
        binding.tvAmountWithdraw.text = amount
        binding.tvFees.text = 1000.toString()
        val totalPrice = amount?.toInt()?.plus(1000)
        binding.tvTotalPrice.text = totalPrice.toString()

        binding.btnProceed.setOnClickListener {
            val moveWithDataIntent = Intent(this@WithdrawReceiptActivity, EnterPinActivity::class.java)
            moveWithDataIntent.putExtra(EnterPinActivity.EXTRA_ACCOUNT, binding.tvBankAccount.text.toString())
            moveWithDataIntent.putExtra(EnterPinActivity.EXTRA_NOMINAL, binding.tvTotalPrice.text.toString())
            moveWithDataIntent.putExtra(EnterPinActivity.EXTRA_BANK, binding.tvBankName.text.toString())
            startActivity(moveWithDataIntent)
        }
    }

    companion object {
        const val EXTRA_NOMINAL = "extra_nominal"
        const val EXTRA_BANK = "extra_bank"
        const val EXTRA_ACCOUNT = "extra_account"
    }
}