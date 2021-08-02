package com.example.recipeapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.api.RecipeApi
import com.example.recipeapp.databinding.ActivityRecipeBinding
import com.example.recipeapp.models.Json4Kotlin_Base
import com.example.recipeapp.models.Recipes
import com.example.recipeapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("Id", 1234)

        val retrofit  = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getRecipeInfo(id)

        call.enqueue(object : Callback<Recipes> {
            override fun onResponse(
                call: Call<Recipes>,
                response: Response<Recipes>
            ) {
                if (response.code() == 200) {

                    val recipe = response.body()
                    Log.e("lmao", "${recipe?.title}")
                    binding.tvRecipeInfoName.text = recipe?.title
                    val text =  recipe?.summary
                    binding.tvRecipeInfoPara.text = text?.replace(Regex("<.*?>"), "")
                    Glide.with(this@RecipeActivity)
                        .load(recipe?.image)
                        .into(binding.imageRecipe)

                }
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRecipeInfoName.text = t.message
            }

        }

        )

    }
}


