package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.UserRequest
import com.example.foodcourtpayclient.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.sign_up)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSignup.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val phoneNumber = binding.edtPhone.text.toString()
            val password = binding.edtPassword.text.toString()
            val moveWithDataIntent = Intent(this@SignupActivity, CreatePinActivity::class.java)
            moveWithDataIntent.putExtra(CreatePinActivity.EXTRA_NAME, name)
            moveWithDataIntent.putExtra(CreatePinActivity.EXTRA_EMAIL, email)
            moveWithDataIntent.putExtra(CreatePinActivity.EXTRA_PHONE, phoneNumber)
            moveWithDataIntent.putExtra(CreatePinActivity.EXTRA_PASSWORD, password)
            Log.d("Data User", "onCreate: $name, $email, $phoneNumber, $password")
            startActivity(moveWithDataIntent)
        }

        binding.tvAccountAlready.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}