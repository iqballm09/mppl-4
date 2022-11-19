package com.example.foodcourtpayclient.layout

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.data.CardResponse
import com.example.foodcourtpayclient.data.UserResponse
import com.example.foodcourtpayclient.databinding.ActivityMainBinding
import com.example.foodcourtpayclient.networking.ApiConfig
import com.example.foodcourtpayclient.networking.ApiService
import com.example.foodcourtpayclient.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mUserPreferences = UserPreferences(this)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        val user = mUserPreferences.getUser()
        Log.d("user", "onCreate: ${user.id}")
        setSaldo()

        binding.tvName.text = user.name

        binding.rvTransaction.setHasFixedSize(true)

        binding.btnPay.setOnClickListener {
            val intent = Intent(this, PayCameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnTopup.setOnClickListener {
            val intent = Intent(this, TopupActivity::class.java)
            startActivity(intent)
        }

        binding.tvMore.setOnClickListener {
            val intent = Intent(this, HistoryTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    private fun setSaldo() {
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getCard(mUserPreferences.getUser().token).enqueue(
            object: Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding.tvCardName.text = response.body()!!.card.name
                        binding.saldo.text = " " + response.body()!!.card.saldo.toString()
                        Log.d("Card Name", "onResponse: ${response.body()!!.card.name}")
                        Log.d("Saldo", "onResponse: ${response.body()!!.card.saldo}")
                    } else {
                        Toast.makeText(applicationContext, "Unable to load Saldo", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("Saldo", "onFailure: ${t.message}", )
                }
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notificationMenu -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.logoutMenu -> {
                mUserPreferences.deleteUser()
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> true
        }
    }

    // camera accessibility
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(this, "Permissions Denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}