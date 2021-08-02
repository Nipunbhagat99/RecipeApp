/* 
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
package com.example.recipeapp.models

import com.google.gson.annotations.SerializedName

data class Recipes (

	@SerializedName("vegetarian") val vegetarian : Boolean,
	@SerializedName("vegan") val vegan : Boolean,
	@SerializedName("glutenFree") val glutenFree : Boolean,
	@SerializedName("dairyFree") val dairyFree : Boolean,
	@SerializedName("veryHealthy") val veryHealthy : Boolean,
	@SerializedName("cheap") val cheap : Boolean,
	@SerializedName("veryPopular") val veryPopular : Boolean,
	@SerializedName("sustainable") val sustainable : Boolean,
	@SerializedName("weightWatcherSmartPoints") val weightWatcherSmartPoints : Int,
	@SerializedName("gaps") val gaps : String,
	@SerializedName("lowFodmap") val lowFodmap : Boolean,
	@SerializedName("aggregateLikes") val aggregateLikes : Int,
	@SerializedName("spoonacularScore") val spoonacularScore : Int,
	@SerializedName("healthScore") val healthScore : Int,
	@SerializedName("creditsText") val creditsText : String,
	@SerializedName("license") val license : String,
	@SerializedName("sourceName") val sourceName : String,
	@SerializedName("pricePerServing") val pricePerServing : Double,
	@SerializedName("extendedIngredients") val extendedIngredients : List<ExtendedIngredients>,
	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("readyInMinutes") val readyInMinutes : Int,
	@SerializedName("servings") val servings : Int,
	@SerializedName("sourceUrl") val sourceUrl : String,
	@SerializedName("image") val image : String,
	@SerializedName("imageType") val imageType : String,
	@SerializedName("summary") val summary : String,
	@SerializedName("cuisines") val cuisines : List<String>,
	@SerializedName("dishTypes") val dishTypes : List<String>,
	@SerializedName("diets") val diets : List<String>,
	@SerializedName("occasions") val occasions : List<String>,
	@SerializedName("instructions") val instructions : String,
	@SerializedName("analyzedInstructions") val analyzedInstructions : List<AnalyzedInstructions>,
	@SerializedName("originalId") val originalId : String,
	@SerializedName("spoonacularSourceUrl") val spoonacularSourceUrl : String
)