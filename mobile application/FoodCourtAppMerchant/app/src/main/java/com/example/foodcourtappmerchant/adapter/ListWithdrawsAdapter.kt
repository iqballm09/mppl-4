package com.example.foodcourtappmerchant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtappmerchant.R
import com.example.foodcourtappmerchant.data.WithdrawsResponse

class ListWithdrawsAdapter(val withdraws: List<WithdrawsResponse>): RecyclerView.Adapter<ListWithdrawsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListWithdrawsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_withdraw, parent, false)
        return ListWithdrawsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListWithdrawsViewHolder, position: Int) {
        return holder.bindView(withdraws[position])
    }

    override fun getItemCount(): Int {
        return withdraws.size
    }
}

class ListWithdrawsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvMethod: TextView = itemView.findViewById(R.id.tv_method)
    private val tvAccountNumber: TextView = itemView.findViewById(R.id.tv_account_number)
    private val tvAmount: TextView = itemView.findViewById(R.id.tv_amount_withdraw)
    private val tvDate: TextView = itemView.findViewById(R.id.tv_date)

    fun bindView(withdraws: WithdrawsResponse) {
        tvMethod.text = withdraws.method
        tvAccountNumber.text = withdraws.accountNumber
        tvAmount.text = withdraws.amount
        tvDate.text = withdraws.date
    }
}