package com.example.foodcourtpayclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.databinding.ActivityPaymentPinBinding

class PaymentPinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentPinBinding.inflate(layoutInflater)
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