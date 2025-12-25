package com.example.onlinefoodorderingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.RowFoodItemBinding
import com.example.onlinefoodorderingapp.models.Food

class FoodAdapter(
    private val foodList: List<Food>,
    private val context: Context
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(val binding: RowFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = RowFoodItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]

        holder.binding.tvFoodName.text = food.name
        holder.binding.tvPrice.text = "₹ ${food.price}"

        holder.binding.btnAddCart.setOnClickListener {
            val db = DBHelper(context)
            db.addToCart(food)
            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = foodList.size
}
