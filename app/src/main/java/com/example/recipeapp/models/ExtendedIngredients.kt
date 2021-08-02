/* 
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
package com.example.recipeapp.models

import com.google.gson.annotations.SerializedName

data class ExtendedIngredients (

	@SerializedName("id") val id : Int,
	@SerializedName("aisle") val aisle : String,
	@SerializedName("image") val image : String,
	@SerializedName("consistency") val consistency : String,
	@SerializedName("name") val name : String,
	@SerializedName("nameClean") val nameClean : String,
	@SerializedName("original") val original : String,
	@SerializedName("originalString") val originalString : String,
	@SerializedName("originalName") val originalName : String,
	@SerializedName("amount") val amount : Double,
	@SerializedName("unit") val unit : String,
	@SerializedName("meta") val meta : List<String>,
	@SerializedName("metaInformation") val metaInformation : List<String>,
	@SerializedName("measures") val measures : Measures
)