package com.example.foodcourtpayclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.foodcourtpayclient.R

class BanksAdapter(val bankList: List<Banks>) : RecyclerView.Adapter<BanksAdapter.BankVH>(){
    class BankVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var bankTv: TextView = itemView.findViewById(R.id.tv_bank_name)
        var mBankingTv: TextView = itemView.findViewById(R.id.tv_1a)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linear_layout)
        var expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandable_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankVH {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_bank, parent, false)

        return  BankVH(view)
    }

    override fun onBindViewHolder(holder: BankVH, position: Int) {
        val banks: Banks = bankList[position]
        holder.bankTv.text = banks.bank
        holder.mBankingTv.text = banks.mbanking

        val isExpandable: Boolean = bankList[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val banks = bankList[position]
            banks.expandable = !banks.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return bankList.size
    }
}