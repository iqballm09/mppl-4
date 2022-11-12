package com.example.foodcourtpayclient.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodcourtpayclient.Banks
import com.example.foodcourtpayclient.BanksAdapter
import com.example.foodcourtpayclient.R
import com.example.foodcourtpayclient.databinding.ActivityBankPaymentBinding

class BankPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBankPaymentBinding

    private val banksList = ArrayList<Banks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.bank_payment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val banksAdapter = BanksAdapter(banksList)
        binding.rvBank.adapter = banksAdapter
        binding.rvBank.setHasFixedSize(true)
    }

    private fun initData() {
        banksList.add(Banks(
            "Bank Negara Indonesia (BNI)",
            "BNI Mobile Banking"
        ))
        banksList.add(
            Banks(
            "Bank Republik Indonesia (BRI)",
            "BRI Mo"
        ))
        banksList.add(
            Banks(
                "Bank Mandiri",
                "Livin' by Mandiri"
        ))
        banksList.add(
            Banks(
                "Bank Central Asia (BCA)",
                "BCA Mobile"
        ))
        banksList.add(
            Banks(
                "Bank Mega",
                "M-Smile"
        ))
    }
}