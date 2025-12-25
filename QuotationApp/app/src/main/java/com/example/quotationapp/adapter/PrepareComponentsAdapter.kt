package com.example.quotationapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.quotationapp.R
import com.example.quotationapp.model.ComponentModel

class PrepareComponentsAdapter(
    private val list: MutableList<ComponentModel>,
    private val selectedList: MutableList<Pair<ComponentModel, Int>>, // Model + Qty
    private val onChanged: () -> Unit
) : RecyclerView.Adapter<PrepareComponentsAdapter.PrepareViewHolder>() {

    class PrepareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgComponent)
        val tvName: TextView = itemView.findViewById(R.id.tvComponentName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvComponentPrice)
        val etQty: EditText = itemView.findViewById(R.id.etQuantity)
        val btnToggle: Button = itemView.findViewById(R.id.btnAddRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrepareViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prepare_component, parent, false)
        return PrepareViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrepareViewHolder, position: Int) {
        val item = list[position]

        holder.tvName.text = item.name
        holder.tvPrice.text = "₹ ${item.price}"
        holder.etQty.setText("1")

        try {
            holder.img.setImageURI(Uri.parse(item.imageUri))
        } catch (e: Exception) {
            holder.img.setImageResource(R.drawable.ic_launcher_background)
        }

        // Check if selected
        val selectedItem = selectedList.find { it.first.id == item.id }
        val isSelected = selectedItem != null

        holder.btnToggle.text = if (isSelected) "Remove" else "Add"

        if (isSelected) {
            // show actual selected qty
            holder.etQty.setText(selectedItem!!.second.toString())
        }

        holder.btnToggle.setOnClickListener {
            val qty = holder.etQty.text.toString().ifEmpty { "1" }.toInt()

            if (isSelected) {
                // REMOVE
                selectedList.removeAll { it.first.id == item.id }
                holder.btnToggle.text = "Add"
            } else {
                // ADD
                selectedList.add(item to qty)
                holder.btnToggle.text = "Remove"
            }

            onChanged()
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = list.size
}
