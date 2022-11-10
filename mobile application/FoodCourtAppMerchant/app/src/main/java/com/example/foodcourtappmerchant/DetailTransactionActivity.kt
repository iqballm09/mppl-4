package com.example.foodcourtappmerchant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.databinding.ActivityDetailTransactionBinding

class DetailTransactionActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailTransactionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.transaction)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}