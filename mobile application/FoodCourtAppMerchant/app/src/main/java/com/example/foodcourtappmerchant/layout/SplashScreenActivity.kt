package com.example.foodcourtappmerchant.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.foodcourtappmerchant.data.UserModel
import com.example.foodcourtappmerchant.databinding.ActivitySplashScreenBinding
import com.example.foodcourtappmerchant.session.UserPreferences

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var mUserPreferences: UserPreferences
    private var isLogin = false
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mUserPreferences = UserPreferences(this)
        getExistingPreference()

        Handler(mainLooper).postDelayed({
            if (isLogin) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }

    private fun getExistingPreference() {
        userModel = mUserPreferences.getUser()
        if (userModel.isLogin) isLogin = true
        Log.d("state", "${userModel.isLogin}")
        Log.d("Splash Screen Activity", "getExistingPreference: ${userModel.token}")
        Log.d("Splash Screen Activity", "getExistingPreference: ${userModel.id}")
    }
}