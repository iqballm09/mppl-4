package com.example.foodcourtpayclient.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.databinding.ActivityBankPaymentBinding

class BankPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBankPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.bank_payment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}