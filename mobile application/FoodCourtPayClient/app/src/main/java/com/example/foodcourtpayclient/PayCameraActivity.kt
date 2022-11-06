package com.example.foodcourtpayclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.databinding.ActivityPayCameraBinding

class PayCameraActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPayCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}