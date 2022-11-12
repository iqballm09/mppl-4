package com.example.foodcourtappmerchant.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.databinding.ActivityTransactionHistoryBinding

class TransactionHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.transaction_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}