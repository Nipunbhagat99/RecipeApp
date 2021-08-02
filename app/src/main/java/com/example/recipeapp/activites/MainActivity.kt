package com.example.recipeapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.adapters.RecipeMainScreenAdapter
import com.example.recipeapp.api.RecipeApi
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.models.Json4Kotlin_Base
import com.example.recipeapp.models.RecipeShortList
import com.example.recipeapp.models.Recipes
import com.example.recipeapp.models.Result
import com.example.recipeapp.utils.Constants.Companion.API_KEY
import com.example.recipeapp.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecipeMainScreenAdapter.RecipeInterface {

    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getRandomRecipe()

        call.enqueue(object : Callback<Json4Kotlin_Base>{
            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: Response<Json4Kotlin_Base>
            ) {
                if (response.code() == 200) {
                    val list = response.body()?.recipes
                    val recipe = list?.get(0)
                    Log.e("lmao", "${recipe?.title}")
                    binding.tvRandomRecipe.text = recipe?.title
                    Glide.with(this@MainActivity)
                        .load(recipe?.image)
                        .into(binding.imageRandom)
                    binding.tvRandomServings.text = recipe?.servings.toString()
                    binding.tvRandomTime.text = recipe?.readyInMinutes.toString()
                }
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRandomRecipe.text = t.message
            }

        }

        )


        setupIndianRV()
//        setupItalianRV()
//        setupChineseRV()
//        setupThaiRV()
//        setupAmericanRV()


    }

    private fun setupAmericanRV() {
        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getMainScreenRecipes("American", API_KEY, 10)

        call.enqueue(object : Callback<RecipeShortList>{
            override fun onResponse(
                call: Call<RecipeShortList>,
                response: Response<RecipeShortList>
            ) {
                if (response.code() == 200) {
                    val list = response.body()?.results!!
                    binding.rvAmerican.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    val recipeMainScreenAdapter = RecipeMainScreenAdapter(this@MainActivity, list,this@MainActivity)
                    binding.rvAmerican.adapter = recipeMainScreenAdapter

                }
            }

            override fun onFailure(call: Call<RecipeShortList>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRandomRecipe.text = t.message
            }

        }

        )
    }

    private fun setupThaiRV() {
        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getMainScreenRecipes("Thai", API_KEY, 10)

        call.enqueue(object : Callback<RecipeShortList>{
            override fun onResponse(
                call: Call<RecipeShortList>,
                response: Response<RecipeShortList>
            ) {
                if (response.code() == 200) {
                    val list = response.body()?.results!!
                    binding.rvThai.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    val recipeMainScreenAdapter = RecipeMainScreenAdapter(this@MainActivity, list,this@MainActivity)
                    binding.rvThai.adapter = recipeMainScreenAdapter

                }
            }

            override fun onFailure(call: Call<RecipeShortList>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRandomRecipe.text = t.message
            }

        }

        )
    }

    private fun setupChineseRV() {
        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getMainScreenRecipes("Chinese", API_KEY, 10)

        call.enqueue(object : Callback<RecipeShortList>{
            override fun onResponse(
                call: Call<RecipeShortList>,
                response: Response<RecipeShortList>
            ) {
                if (response.code() == 200) {
                    val list = response.body()?.results!!
                    binding.rvChinese.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    val recipeMainScreenAdapter = RecipeMainScreenAdapter(this@MainActivity, list,this@MainActivity)
                    binding.rvChinese.adapter = recipeMainScreenAdapter

                }
            }

            override fun onFailure(call: Call<RecipeShortList>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRandomRecipe.text = t.message
            }

        }

        )
    }

    private fun setupItalianRV() {
        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getMainScreenRecipes("italian", API_KEY, 10)

        call.enqueue(object : Callback<RecipeShortList>{
            override fun onResponse(
                call: Call<RecipeShortList>,
                response: Response<RecipeShortList>
            ) {
                if (response.code() == 200) {
                    val list = response.body()?.results!!
                    binding.rvItalian.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    val recipeMainScreenAdapter = RecipeMainScreenAdapter(this@MainActivity, list,this@MainActivity)
                    binding.rvItalian.adapter = recipeMainScreenAdapter

                }
            }

            override fun onFailure(call: Call<RecipeShortList>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRandomRecipe.text = t.message
            }

        }

        )
    }

    private fun setupIndianRV(){
        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeApi::class.java)
        val call = service.getMainScreenRecipes("indian", API_KEY, 1)

        call.enqueue(object : Callback<RecipeShortList>{
            override fun onResponse(
                call: Call<RecipeShortList>,
                response: Response<RecipeShortList>
            ) {
                if (response.code() == 200) {
                    val list = response.body()?.results!!
                    binding.rvIndian.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    val recipeMainScreenAdapter = RecipeMainScreenAdapter(this@MainActivity, list,this@MainActivity)
                    binding.rvIndian.adapter = recipeMainScreenAdapter

                }
            }

            override fun onFailure(call: Call<RecipeShortList>, t: Throwable) {
                Log.e("lmao", "${t.message}")
                binding.tvRandomRecipe.text = t.message
            }

        }

        )



    }

    override fun openRecipeActivity(id: Int) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("Id", id)
        startActivity(intent)
    }


}


