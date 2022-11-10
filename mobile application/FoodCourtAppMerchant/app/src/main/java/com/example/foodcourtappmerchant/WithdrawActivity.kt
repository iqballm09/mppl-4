package com.example.foodcourtappmerchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.foodcourtappmerchant.databinding.ActivityWithdrawBinding

class WithdrawActivity : AppCompatActivity() {
    lateinit var binding: ActivityWithdrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWithdrawBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.withdraw)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnWithdraw.setOnClickListener {
            val intent = Intent(this, WithdrawReceiptActivity::class.java)
            startActivity(intent)
        }

        val bankList = arrayOf("BNI", "BRI", "Bank Mandiri", "BCA", "Bank Mega", "ATM Bersama", "Bank Permata")

        val arrayAdapter = ArrayAdapter(this,  android.R.layout.simple_spinner_dropdown_item, bankList)

        binding.spinnerBank.adapter = arrayAdapter
        binding.spinnerBank.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
}