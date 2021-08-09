package com.example.recipeapp.api

import com.example.recipeapp.models.Cuisine
import com.example.recipeapp.models.Json4Kotlin_Base
import com.example.recipeapp.models.RecipeShortList
import com.example.recipeapp.models.Recipes
import com.example.recipeapp.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/random?apiKey=$API_KEY&number=1")
    fun getRandomRecipe() : Call<Json4Kotlin_Base>


    @GET("recipes/complexSearch?")
    fun getMainScreenRecipes( @Query("cuisine") cuisine: String, @Query("apiKey") apiKey: String,@Query("number") number: Int): Call<RecipeShortList>

    @GET("recipes/{id}/information?apiKey=$API_KEY")
    fun getRecipeInfo(@Path("id") id : Int):  Call<Recipes>

    @GET("recipes/complexSearch?")
    fun getCuisineRecipes( @Query("cuisine") cuisine: String, @Query("apiKey") apiKey: String,@Query("number") number: Int,@Query("addRecipeInformation") addRecipeInformation : Boolean): Call<Cuisine>
}