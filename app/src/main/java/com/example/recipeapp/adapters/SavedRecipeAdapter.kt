package com.example.recipeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.CuisineRecipeViewBinding
import com.example.recipeapp.models.ResultX
import com.example.recipeapp.models.SavedRecipe

class SavedRecipeAdapter(private val context: Context, private val list: List<SavedRecipe>, val listener : RecipeInterface): RecyclerView.Adapter<SavedRecipeAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CuisineRecipeViewBinding.inflate(
                LayoutInflater.from(context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name : String= list[position].name

        if(name.length >30){
            var newName = name.subSequence(0,28)
            newName = "$newName..."
            holder.binding.tvCuisineRecipeName.text = newName
        }
        else{
            holder.binding.tvCuisineRecipeName.text = name
        }
        Glide.with(context)
            .load(list[position].url)
            .into(holder.binding.imageCuisine)
        holder.binding.tvCuisineServings.text = list[position].servings.toString()
        holder.binding.tvCuisineTime.text = list[position].time.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RecipeInterface{
        fun openRecipeActivity(id : Int)
    }

    inner class ViewHolder(val binding : CuisineRecipeViewBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{


        init{
            binding.cvCuisine.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            listener.openRecipeActivity(list[adapterPosition].id)
        }
    }
}