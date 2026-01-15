package com.example.easyfood.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.pojo.Meal

class FavoritesMealAdapter : RecyclerView.Adapter<FavoritesMealAdapter.FavoritesMealViewHolder>() {

    // ViewHolder
    inner class FavoritesMealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMeal: ImageView = itemView.findViewById(R.id.imgMeal)
        val tvMealName: TextView = itemView.findViewById(R.id.tvMealName)
        val tvMealCategory: TextView = itemView.findViewById(R.id.tvMealCategory)
    }

    // DiffUtil for AsyncListDiffer
    private val diffCallback = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
            oldItem.idMeal == newItem.idMeal

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_meal_item, parent, false)
        return FavoritesMealViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesMealViewHolder, position: Int) {
        val meal = differ.currentList[position]

        holder.tvMealName.text = meal.strMeal ?: "No Name"
        holder.tvMealCategory.text = meal.strCategory ?: "Unknown"

        Glide.with(holder.itemView.context)
            .load(meal.strMealThumb)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imgMeal)
    }

    override fun getItemCount(): Int = differ.currentList.size

    // Submit list to adapter
    fun submitList(meals: List<Meal>) {
        differ.submitList(meals)
    }
}

