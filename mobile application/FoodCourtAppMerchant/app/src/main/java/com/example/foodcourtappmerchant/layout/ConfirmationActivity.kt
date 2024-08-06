package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    lateinit var binding: ActivityConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val amount = intent.getStringExtra(EXTRA_NOMINAL)
        val bank = intent.getStringExtra(EXTRA_BANK)
        val accountNumber = intent.getStringExtra(EXTRA_ACCOUNT)

        binding.tvBankName.text = bank
        binding.tvBankAccount.text = accountNumber
        binding.tvTotalPrice.text = amount
        binding.tvAmount.text = (amount?.toInt()?.minus(1000)).toString()

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_NOMINAL = "extra_nominal"
        const val EXTRA_BANK = "extra_bank"
        const val EXTRA_ACCOUNT = "extra_account"
    }
}