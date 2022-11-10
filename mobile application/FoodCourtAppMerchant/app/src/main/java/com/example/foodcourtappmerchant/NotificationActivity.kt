package com.example.foodcourtappmerchant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtappmerchant.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.notification)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}