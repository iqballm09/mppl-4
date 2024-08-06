package com.example.foodcourtpayclient.layout

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.LoginRequest
import com.example.foodcourtpayclient.data.LoginResponse
import com.example.foodcourtpayclient.databinding.ActivityLoginBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mUserPreferences = UserPreferences(this)
        playAnimation()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val user = LoginRequest(email, password)
            Log.d("Credentials", "onCreate: $email $password")
            postLogin(user)
        }
    }

    private fun playAnimation() {
        val logo = ObjectAnimator.ofFloat(binding.ivLogo, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
        val forgot = ObjectAnimator.ofFloat(binding.tvForgotPassword, View.ALPHA, 1f).setDuration(500)
        val noAccount = ObjectAnimator.ofFloat(binding.tvNoAccount, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(logo, email, password, login, forgot, noAccount)
            start()
        }
    }

    private fun postLogin(user: LoginRequest) {
        val userPreferences = UserPreferences(this)
        showLoading(true)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.postLogin(user).enqueue(
            object:Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful && response.body() != null) {
                        userPreferences.setUser(response.body()!!.token, response.body()!!.name, response.body()!!.id)
                        Toast.makeText(applicationContext, "Welcome to FoodCourt Pay!", Toast.LENGTH_SHORT).show()
                        Log.d("Token", "onResponse: ${response.body()?.token} ${response.body()?.name} ${response.body()?.id}")
                        moveActivity()
                    } else {
                        Toast.makeText(applicationContext, response.message()
                            , Toast.LENGTH_SHORT).show()
                        Log.d("LoginActivity", "onResponse: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    showLoading(false)
                    Log.e("LoginActivity", "onFailure: ${t.message}")
                }
            }
        )
    }

    private fun moveActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}