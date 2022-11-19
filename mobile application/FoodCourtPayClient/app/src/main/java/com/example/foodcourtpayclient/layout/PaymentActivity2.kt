package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.databinding.ActivityPayment2Binding

class PaymentActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityPayment2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayment2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.billing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnProceed.setOnClickListener {
            val binding = Intent(this, PaymentPinActivity::class.java)
            startActivity(binding)
        }
    }
}