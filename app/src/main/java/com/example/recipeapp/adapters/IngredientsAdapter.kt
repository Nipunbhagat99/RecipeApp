package com.example.recipeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.IngredientsViewBinding
import com.example.recipeapp.models.ExtendedIngredients
import com.example.recipeapp.models.Result

class IngredientsAdapter(private val context: Context, private val list: List<ExtendedIngredients>): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IngredientsViewBinding.inflate(
                LayoutInflater.from(context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvIngredientsName.text = list[position].originalString

    }

    override fun getItemCount(): Int {
        return list.size
    }



    inner class ViewHolder(val binding: IngredientsViewBinding) : RecyclerView.ViewHolder(binding.root)

}