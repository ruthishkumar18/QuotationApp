package com.example.voicequoteai.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.voicequoteai.data.model.Quotation
import com.example.voicequoteai.databinding.ItemQuotationRowBinding

class HistoryAdapter :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val items = mutableListOf<Quotation>()

    fun submitList(list: List<Quotation>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemQuotationRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Quotation) {
            binding.txtClient.text = item.clientName
            binding.txtService.text = item.service
            binding.txtAmount.text = "₹ ${item.amount}"
            binding.txtDate.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuotationRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
