package com.example.recipeapp.models

data class SavedRecipe (
    val id : Int,
    val name : String,
    val time : Int,
    val servings : Int,
    val url : String
        )