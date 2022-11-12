package com.example.foodcourtappmerchant.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawHistoryBinding

class WithdrawHistoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}