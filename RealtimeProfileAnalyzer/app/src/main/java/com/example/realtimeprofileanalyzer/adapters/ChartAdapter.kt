package com.example.realtimeprofileanalyzer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimeprofileanalyzer.R

class ChartAdapter(
    private val chartData: List<String>
) : RecyclerView.Adapter<ChartAdapter.ChartViewHolder>() {

    class ChartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvChartItem: TextView = view.findViewById(R.id.tvChartItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chart_row, parent, false)
        return ChartViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        holder.tvChartItem.text = chartData[position]
    }

    override fun getItemCount(): Int = chartData.size
}
