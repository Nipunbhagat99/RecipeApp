package com.example.recipeapp.models

data class RecipeShortList(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)