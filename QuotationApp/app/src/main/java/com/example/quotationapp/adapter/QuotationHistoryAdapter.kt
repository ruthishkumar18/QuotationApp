package com.example.quotationapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotationapp.R
import com.example.quotationapp.model.QuotationModel

class QuotationHistoryAdapter(
    private val list: MutableList<QuotationModel>,
    private val onOpen: (QuotationModel) -> Unit,
    private val onShare: (QuotationModel) -> Unit,
    private val onAccept: (QuotationModel) -> Unit,
    private val onReject: (QuotationModel) -> Unit
) : RecyclerView.Adapter<QuotationHistoryAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvCustomerName)
        val tvMobile: TextView = view.findViewById(R.id.tvMobile)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)

        val btnOpen: Button = view.findViewById(R.id.btnOpenQuotation)
        val btnShare: Button = view.findViewById(R.id.btnShareQuotation)
        val btnAccept: Button = view.findViewById(R.id.btnAccept)
        val btnReject: Button = view.findViewById(R.id.btnReject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quotation, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]

        // -------------------------------
        // BASIC DETAILS
        // -------------------------------
        holder.tvName.text = item.customerName
        holder.tvMobile.text = "Mobile: ${item.customerMobile}"
        holder.tvEmail.text = "Email: ${item.customerEmail}"
        holder.tvDate.text = "Date: ${item.date}"
        holder.tvStatus.text = item.status

        // -------------------------------
        // STATUS COLOR
        // -------------------------------
        when (item.status) {
            "PENDING" -> {
                holder.tvStatus.setTextColor(Color.parseColor("#FF9800")) // Orange
                holder.btnAccept.visibility = View.VISIBLE
                holder.btnReject.visibility = View.VISIBLE
            }
            "ACCEPTED" -> {
                holder.tvStatus.setTextColor(Color.parseColor("#4CAF50")) // Green
                holder.btnAccept.visibility = View.GONE
                holder.btnReject.visibility = View.GONE
            }
            "REJECTED" -> {
                holder.tvStatus.setTextColor(Color.parseColor("#F44336")) // Red
                holder.btnAccept.visibility = View.GONE
                holder.btnReject.visibility = View.GONE
            }
            else -> {
                holder.tvStatus.setTextColor(Color.DKGRAY)
                holder.btnAccept.visibility = View.GONE
                holder.btnReject.visibility = View.GONE
            }
        }

        // -------------------------------
        // BUTTON ACTIONS
        // -------------------------------
        holder.btnOpen.setOnClickListener {
            onOpen(item)
        }

        holder.btnShare.setOnClickListener {
            onShare(item)
        }

        holder.btnAccept.setOnClickListener {
            onAccept(item)
        }

        holder.btnReject.setOnClickListener {
            onReject(item)
        }
    }

    override fun getItemCount(): Int = list.size
}
