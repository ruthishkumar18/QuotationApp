package com.example.quotationapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotationapp.R
import com.example.quotationapp.model.ComponentModel
import java.io.File

class ComponentsAdapter(
    private val list: MutableList<ComponentModel>,
    private val onEdit: (ComponentModel) -> Unit,
    private val onDelete: (ComponentModel) -> Unit
) : RecyclerView.Adapter<ComponentsAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val tvSno: TextView = item.findViewById(R.id.tvSno)
        val img: ImageView = item.findViewById(R.id.imgComponent)
        val tvName: TextView = item.findViewById(R.id.tvName)
        val tvPrice: TextView = item.findViewById(R.id.tvPrice)
        val btnEdit: Button = item.findViewById(R.id.btnEdit)
        val btnDelete: Button = item.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_component_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvSno.text = "${position + 1}"
        holder.tvName.text = item.name
        holder.tvPrice.text = "₹ ${item.price}"

        try {
            holder.img.setImageURI(Uri.fromFile(File(item.imageUri)))
        } catch (e: Exception) {
            holder.img.setImageResource(R.drawable.ic_launcher_background)
        }

        holder.btnEdit.setOnClickListener { onEdit(item) }
        holder.btnDelete.setOnClickListener { onDelete(item) }
    }

    override fun getItemCount(): Int = list.size
}
