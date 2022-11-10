package com.example.foodcourtpayclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.sign_up)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}