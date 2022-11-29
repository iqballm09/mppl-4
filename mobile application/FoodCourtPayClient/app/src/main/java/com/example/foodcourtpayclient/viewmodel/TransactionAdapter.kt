package com.example.foodcourtpayclient.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtpayclient.data.PaymentResponse
import com.example.foodcourtpayclient.databinding.ItemTransactionBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.ListViewHolder>() {
    private val transaction = ArrayList<PaymentResponse>()
    inner class ListViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: PaymentResponse) {
            binding.apply {
                tvTransaction.text = transaction.foodcourtName
                tvLocation.text = transaction.merchantName
                tvAmount.text = transaction.amount
                tvDate.text = transaction.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(transaction[position])
    }

    override fun getItemCount(): Int = transaction.size

}