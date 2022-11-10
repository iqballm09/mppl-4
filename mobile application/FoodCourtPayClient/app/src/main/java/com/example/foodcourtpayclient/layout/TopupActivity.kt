package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.databinding.ActivityTopupBinding

class TopupActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.topup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tvHistory.setOnClickListener {
            val intent = Intent(this, HistoryTransactionActivity::class.java)
            startActivity(intent)
        }

        binding.btnBank.setOnClickListener {
            val intent = Intent(this, BankPaymentActivity::class.java)
            startActivity(intent)
        }
    }
}