package com.example.recipeapp.models

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredients>,
    val length: Length,
    val number: Int,
    val step: String
)