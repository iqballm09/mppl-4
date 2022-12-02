package com.example.foodcourtappmerchant.layout
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.adapter.ListTransactionAdapter
import com.example.foodcourtappmerchant.data.ListTransactionResponse
import com.example.foodcourtappmerchant.data.UserResponse
import com.example.foodcourtappmerchant.databinding.ActivityMainBinding
import com.example.foodcourtappmerchant.networking.ApiConfig
import com.example.foodcourtappmerchant.networking.ApiService
import com.example.foodcourtappmerchant.session.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mUserPreferences = UserPreferences(this)
        setData()
        setTransactionList()

        binding.btnWithdraw.setOnClickListener {
            val intent = Intent(this, WithdrawMenuActivity::class.java)
            startActivity(intent)
        }

        binding.tvMore.setOnClickListener {
            val intent = Intent(this, TransactionHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setData() {
        showLoadingCard(true)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getCard(mUserPreferences.getUser().token).enqueue(
            object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        showLoadingCard(false)
                        binding.tvName.text = response.body()!!.merchant.name.toString()
                        binding.saldo.text = " " + response.body()!!.merchant.income.toString()
                        binding.tvBusiness.text = response.body()!!.merchant.name.toString()
                    } else {
                        Toast.makeText(applicationContext, "Unable to load data", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("Data", "onFailure: ${t.message}")
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
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

    private fun setTransactionList() {
        showLoading(true)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_transaction)
        val retrofit = ApiConfig.buildService(ApiService::class.java)
        retrofit.getTransactions(mUserPreferences.getUser().token).enqueue(
            object : Callback<ListTransactionResponse> {
                override fun onResponse(
                    call: Call<ListTransactionResponse>,
                    response: Response<ListTransactionResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.d("List Success", response.body().toString())
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = ListTransactionAdapter( response.body()!!.payments.asReversed())
                        }
                        showLoading(false)
                    }
                }

                override fun onFailure(call: Call<ListTransactionResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("Failure :", t.message!!)
                }
            }
        )
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showLoadingCard(state: Boolean) {
        if (state) {
            binding.progressBarCard.visibility = View.VISIBLE
        } else {
            binding.progressBarCard.visibility = View.GONE
        }
    }
}