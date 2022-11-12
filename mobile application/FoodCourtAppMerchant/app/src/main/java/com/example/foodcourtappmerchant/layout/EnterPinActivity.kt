package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.databinding.ActivityEnterPinBinding

class EnterPinActivity : AppCompatActivity() {
    lateinit var binding: ActivityEnterPinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEnterPinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.pin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnProceed.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}