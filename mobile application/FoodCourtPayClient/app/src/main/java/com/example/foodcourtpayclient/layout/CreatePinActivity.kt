package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.databinding.ActivityCreatePinBinding

class CreatePinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.create_pin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnProceed.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}