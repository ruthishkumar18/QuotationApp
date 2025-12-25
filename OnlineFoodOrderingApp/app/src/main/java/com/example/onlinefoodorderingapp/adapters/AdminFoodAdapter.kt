package com.example.onlinefoodorderingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.RowFoodItemBinding
import com.example.onlinefoodorderingapp.models.Food

class AdminFoodAdapter(
    private val foodList: MutableList<Food>,
    private val context: Context
) : RecyclerView.Adapter<AdminFoodAdapter.AdminFoodViewHolder>() {

    inner class AdminFoodViewHolder(val binding: RowFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminFoodViewHolder {
        val binding = RowFoodItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdminFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminFoodViewHolder, position: Int) {
        val food = foodList[position]

        holder.binding.tvFoodName.text = food.name
        holder.binding.tvPrice.text = "₹ ${food.price}"

        holder.binding.btnAddCart.text = "Remove"

        holder.binding.btnAddCart.setOnClickListener {
            val db = DBHelper(context)
            db.deleteFood(food.id)
            foodList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = foodList.size
}
