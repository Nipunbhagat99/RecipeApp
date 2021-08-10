package com.example.recipeapp.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.adapters.IngredientsAdapter
import com.example.recipeapp.api.RecipeApi
import com.example.recipeapp.database.DatabaseHandler
import com.example.recipeapp.databinding.ActivityRecipeBinding
import com.example.recipeapp.models.Recipes
import com.example.recipeapp.models.SavedRecipe
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

        binding.ibBackRecipe.setOnClickListener{
            onBackPressed()
        }

        val id = intent.getIntExtra("Id", 1234)
        lateinit var recipe : Recipes
        binding.fabSave.setOnClickListener {
            val id = recipe.id
            val dbHandler = DatabaseHandler(this)

            if(dbHandler.exists(id)){
                dbHandler.deleteResult(id)
                binding.fabSave.setImageResource(R.drawable.ic_unsaved)

            }
            else{
                val saveRecipe = SavedRecipe(recipe.id , recipe.title,recipe.readyInMinutes ,recipe.servings, recipe.image)
                dbHandler.addRecipe(saveRecipe)
                binding.fabSave.setImageResource(R.drawable.ic_saved)
            }

        }


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

                    recipe = response.body()!!
                    Log.e("lmao", "${recipe?.title}")
                    binding.tvRecipeInfoName.text = recipe?.title
                    val text =  recipe?.summary
                    binding.fabSave.isClickable = true
                    binding.tvRecipeInfoName.visibility = View.VISIBLE
                    val dbHandler = DatabaseHandler(this@RecipeActivity)
                    if(dbHandler.exists(recipe.id)){
                        binding.fabSave.setImageResource(R.drawable.ic_saved)
                    }
                    else{
                        binding.fabSave.setImageResource(R.drawable.ic_unsaved)
                    }
                    binding.tvRecipeInfoPara.text = text?.replace(Regex("<.*?>"), "")
                    binding.tvRecipeInfoPara.visibility = View.VISIBLE
                    if(!this@RecipeActivity.isFinishing) {
                        Glide.with(this@RecipeActivity)
                            .load(recipe?.image)
                            .into(binding.imageRecipe)
                    }
                    binding.rvRecipeInfoIngredients.layoutManager = LinearLayoutManager(this@RecipeActivity, LinearLayoutManager.VERTICAL, false)
                    val ingredientsAdapterAdapter = IngredientsAdapter(this@RecipeActivity, recipe!!.extendedIngredients)
                    binding.rvRecipeInfoIngredients.adapter = ingredientsAdapterAdapter

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


