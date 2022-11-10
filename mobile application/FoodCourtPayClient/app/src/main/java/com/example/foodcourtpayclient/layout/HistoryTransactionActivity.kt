package com.example.foodcourtpayclient.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.databinding.ActivityHistoryTransactionBinding

class HistoryTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.transaction_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}