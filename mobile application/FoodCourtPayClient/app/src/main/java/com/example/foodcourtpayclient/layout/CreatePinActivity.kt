package com.example.foodcourtpayclient.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.UserRequest
import com.example.foodcourtpayclient.data.UserResponse
import com.example.foodcourtpayclient.databinding.ActivityCreatePinBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.create_pin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(EXTRA_NAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val password = intent.getStringExtra(EXTRA_PASSWORD)
        val phoneNumber = intent.getStringExtra(EXTRA_PHONE)
        Log.d("Create Pin", "onCreate: $name, $email, $phoneNumber, $password")

        binding.btnProceed.setOnClickListener {
            if (binding.edtPin.text.toString().isEmpty() || binding.edtRePin.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Pin", Toast.LENGTH_SHORT).show()
            } else if (binding.edtPin.text.toString() != binding.edtRePin.text.toString()) {
                Toast.makeText(this, "Please check your pin", Toast.LENGTH_SHORT).show()
            } else {
                val pinNumber = binding.edtPin.text.toString()
                val user = UserRequest(name, email, password, phoneNumber, pinNumber)
                postRegister(user)
            }
        }
    }

    private fun postRegister(user: UserRequest) {
        showLoading(true)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.registerUser(user).enqueue(
            object :Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Account Created, Please Sign In", Toast.LENGTH_SHORT).show()
                        Log.d("Create Account", "onResponse: ${response.body()!!.user.id} created")
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("Create Account", "onResponse: ${response.message()}")
                        Toast.makeText(applicationContext, "Account Created, Please Sign In", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    showLoading(false)
                    Log.e("Create Account", "onFailure: ${t.message}")
                }
            }
        )
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PASSWORD = "extra_password"
        const val EXTRA_PHONE = "extra_phone"
    }
}