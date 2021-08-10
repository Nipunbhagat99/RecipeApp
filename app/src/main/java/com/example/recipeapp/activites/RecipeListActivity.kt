package com.example.recipeapp.activites

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.adapters.CuisineRecipesAdapter
import com.example.recipeapp.adapters.SavedRecipeAdapter
import com.example.recipeapp.api.RecipeApi
import com.example.recipeapp.database.DatabaseHandler
import com.example.recipeapp.databinding.ActivityCuisineBinding
import com.example.recipeapp.models.Cuisine
import com.example.recipeapp.utils.Constants
import com.example.recipeapp.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeListActivity : AppCompatActivity(), CuisineRecipesAdapter.RecipeInterface, SavedRecipeAdapter.RecipeInterface {
    private lateinit var binding : ActivityCuisineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuisineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibBackCuisine.setOnClickListener{
            onBackPressed()
        }


        val cuisine = intent.getStringExtra("cuisine")
        var query = intent.getStringExtra("query")


        if(cuisine == "SAVED"){
            getSavedRecipes()
            binding.tvHeading.text = cuisine?.uppercase()
        }
        else if(cuisine != "none") {
            getCuisineRecipes(cuisine)
            binding.tvHeading.text = cuisine?.uppercase()
        }
        else if(query != "none") {
            var answer = query?.replace(" ","+")
            val re = Regex("[^A-Za-z0-9 ]")
            answer = re.replace(answer!!, "") // works
            answer.replace("\\s+".toRegex(), "+")
            getQueryRecipes(answer!!)
            binding.tvHeading.text = "Search results for : $query"
            binding.tvHeading.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            binding.tvHeading.typeface = Typeface.DEFAULT
        }


    }

    private fun getCuisineRecipes(cuisine: String?) {
        val retrofit  = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getCuisineRecipes(cuisine!!,API_KEY, 20,true)

        call.enqueue(object : Callback<Cuisine> {
            override fun onResponse(
                call: Call<Cuisine>,
                response: Response<Cuisine>
            ) {
                if (response.code() == 200) {


                    val list = response.body()?.results!!
                    Log.i("lmqo", "${list[0].title}")
                    binding.tvNoRecipes.visibility = View.GONE
                    binding.rvCuisine.visibility = View.VISIBLE
                    binding.rvCuisine.layoutManager = LinearLayoutManager(this@RecipeListActivity, LinearLayoutManager.VERTICAL,false)
                    val cuisineRecipesAdapter = CuisineRecipesAdapter(this@RecipeListActivity, list ,this@RecipeListActivity)
                    binding.rvCuisine.adapter = cuisineRecipesAdapter

                }
            }

            override fun onFailure(call: Call<Cuisine>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvHeading.text = t.message
            }

        }

        )
    }

    private fun getQueryRecipes(query : String){
        val retrofit  = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getQueryRecipes(query!!,API_KEY, 20,true)

        call.enqueue(object : Callback<Cuisine> {
            override fun onResponse(
                call: Call<Cuisine>,
                response: Response<Cuisine>
            ) {
                if (response.code() == 200) {


                    val list = response.body()?.results!!
                    if(list.isEmpty()){
                        binding.tvNoRecipes.text = "No recipes found"
                    }
                    else{
                        binding.tvNoRecipes.visibility = View.GONE
                        binding.rvCuisine.visibility = View.VISIBLE
                    }
                    binding.rvCuisine.layoutManager = LinearLayoutManager(this@RecipeListActivity, LinearLayoutManager.VERTICAL,false)
                    val cuisineRecipesAdapter = CuisineRecipesAdapter(this@RecipeListActivity, list ,this@RecipeListActivity)
                    binding.rvCuisine.adapter = cuisineRecipesAdapter

                }
            }

            override fun onFailure(call: Call<Cuisine>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvHeading.text = t.message
            }

        }

        )

    }

    override fun openRecipeActivity(id: Int) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("Id", id)
        startActivity(intent)
    }

    private fun getSavedRecipes(){
        val dbHandler = DatabaseHandler(this)
        val list = dbHandler.getRecipeList()
        if(list.isEmpty()){
            binding.tvNoRecipes.text = "No recipes saved"
        }
        else{
            binding.tvNoRecipes.visibility = View.GONE
            binding.rvCuisine.visibility = View.VISIBLE
        }
        binding.rvCuisine.layoutManager = LinearLayoutManager(this@RecipeListActivity, LinearLayoutManager.VERTICAL,false)
        val savedRecipeAdapter = SavedRecipeAdapter(this@RecipeListActivity, list ,this@RecipeListActivity)
        binding.rvCuisine.adapter = savedRecipeAdapter

    }




    override fun onResume() {
        super.onResume()
        getSavedRecipes()
    }
}