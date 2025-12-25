package com.example.onlinefoodorderingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinefoodorderingapp.database.DBHelper
import com.example.onlinefoodorderingapp.databinding.RowCartItemBinding
import com.example.onlinefoodorderingapp.models.CartItem

class CartAdapter(
    private var cartList: MutableList<CartItem>,
    private val context: Context
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: RowCartItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = RowCartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]

        holder.binding.tvSno.text = (position + 1).toString()
        holder.binding.tvFoodName.text = item.foodName
        holder.binding.tvQuantity.text = item.quantity.toString()
        holder.binding.tvPrice.text = "₹ ${item.price * item.quantity}"

        holder.binding.btnRemove.setOnClickListener {
            val db = DBHelper(context)
            db.removeFromCart(item.foodName)
            cartList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartList.size)
        }
    }

    override fun getItemCount(): Int = cartList.size
}
