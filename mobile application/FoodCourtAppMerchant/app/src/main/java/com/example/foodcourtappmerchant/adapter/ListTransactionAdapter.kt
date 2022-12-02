package com.example.foodcourtappmerchant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.data.PaymentsResponse

class ListTransactionAdapter(val payments: List<PaymentsResponse>): RecyclerView.Adapter<ListTransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ListTransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListTransactionViewHolder, position: Int) {
        return holder.bindView(payments[position])
    }

    override fun getItemCount(): Int {
        return payments.size
    }
}

class ListTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTransaction: TextView = itemView.findViewById(R.id.tv_transaction)
    private val tvLocation: TextView = itemView.findViewById(R.id.tv_location)
    private val tvAmount: TextView = itemView.findViewById(R.id.tv_amount)
    private val tvDate: TextView = itemView.findViewById(R.id.tv_date)

    fun bindView(payments: PaymentsResponse) {
        tvTransaction.text = payments.name_user
        tvLocation.text = payments.merchantName
        tvAmount.text = payments.amount
        tvDate.text = payments.date
    }
}