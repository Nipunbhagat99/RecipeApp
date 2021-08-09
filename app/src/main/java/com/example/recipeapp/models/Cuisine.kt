package com.example.recipeapp.models

data class Cuisine(
    val number: Int,
    val offset: Int,
    val results: List<ResultX>,
    val totalResults: Int
)