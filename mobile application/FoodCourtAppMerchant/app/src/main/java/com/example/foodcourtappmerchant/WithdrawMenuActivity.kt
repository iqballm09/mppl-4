package com.example.foodcourtappmerchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawBinding
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawMenuBinding

class WithdrawMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithdrawMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.withdraw)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnBank.setOnClickListener {
            val intent = Intent(this, WithdrawActivity::class.java)
            startActivity(intent)
        }
    }
}