package com.example.realtimeprofileanalyzer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimeprofileanalyzer.R

class TableAdapter(
    private val tableData: List<Pair<String, String>>
) : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    class TableViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvKey: TextView = view.findViewById(R.id.tvKey)
        val tvValue: TextView = view.findViewById(R.id.tvValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table_row, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val item = tableData[position]
        holder.tvKey.text = item.first
        holder.tvValue.text = item.second
    }

    override fun getItemCount(): Int = tableData.size
}
