package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawReceiptBinding

class WithdrawReceiptActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawReceiptBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawReceiptBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.withdraw)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnProceed.setOnClickListener {
            val intent = Intent(this, EnterPinActivity::class.java)
            startActivity(intent)
        }
    }
}