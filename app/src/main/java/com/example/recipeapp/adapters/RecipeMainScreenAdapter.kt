package com.example.recipeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.RecipeMainScreenViewBinding
import com.example.recipeapp.models.RecipeShortList
import com.example.recipeapp.models.Result
import retrofit2.Callback

class RecipeMainScreenAdapter(private val context: Context, private val list: List<Result> , val listener : RecipeInterface): RecyclerView.Adapter<RecipeMainScreenAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecipeMainScreenViewBinding.inflate(
                LayoutInflater.from(context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name : String= list[position].title

        if(name.length >12){
            var newName = name.subSequence(0,10)
            newName = "$newName..."
            holder.binding.tvRecipeMain.text = newName
        }
        else{
            holder.binding.tvRecipeMain.text = name
        }
        Glide.with(context)
            .load(list[position].image)
            .into(holder.binding.ivRecipeMain)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RecipeInterface{
        fun openRecipeActivity(id : Int)
    }

    inner class ViewHolder(val binding : RecipeMainScreenViewBinding ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{


        init{
            binding.cvRecipeMainRv.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            listener.openRecipeActivity(list[adapterPosition].id)
        }
    }
}

