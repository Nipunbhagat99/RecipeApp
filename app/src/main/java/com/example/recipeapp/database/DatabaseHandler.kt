package com.example.recipeapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.recipeapp.models.SavedRecipe

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context,  DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "RecipeDatabase"
        private const val DATABASE_VERSION  = 2
        private const val TABLE_SAVED_RECIPES = "SavedRecipes"

        //Column names
        private const val KEY_SNO = "Sno"
        private const val KEY_ID = "_id"
        private const val KEY_RECIPE_NAME = "Recipe_Name"
        private const val KEY_RECIPE_TIME = "Recipe_Time"
        private const val KEY_RECIPE_SERVINGS = "Recipe_Servings"
        private const val KEY_RECIPE_IMAGE = "Recipe_Image"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val createResultTable = ("CREATE TABLE " + TABLE_SAVED_RECIPES + "("
                + KEY_SNO+ " INTEGER PRIMARY KEY,"
                + KEY_ID+ " INTEGER,"
                + KEY_RECIPE_NAME + " TEXT,"
                + KEY_RECIPE_TIME + " INTEGER,"
                + KEY_RECIPE_SERVINGS + " INTEGER,"
                + KEY_RECIPE_IMAGE+ " TEXT)"
                )
        db?.execSQL(createResultTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_SAVED_RECIPES")
        onCreate(db)
    }

    fun addRecipe(savedRecipe : SavedRecipe){
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_ID , savedRecipe.id)
        contentValues.put(KEY_RECIPE_NAME , savedRecipe.name)
        contentValues.put(KEY_RECIPE_TIME , savedRecipe.time)
        contentValues.put(KEY_RECIPE_SERVINGS , savedRecipe.servings)
        contentValues.put(KEY_RECIPE_IMAGE , savedRecipe.url)



        // Inserting Row
        val result = db.insert(TABLE_SAVED_RECIPES, null, contentValues)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection

    }



    fun deleteResult(id : Int) : Int{
        val db = this.writableDatabase
        val success = db.delete(TABLE_SAVED_RECIPES, "$KEY_ID=$id", null)
        db.close()
        return success
    }

    fun exists(id : Int) : Boolean {
        val db = this.readableDatabase
        val selectQuery = "SELECT COUNT(*) FROM $TABLE_SAVED_RECIPES WHERE $KEY_ID = $id"
        val success = db.rawQuery(selectQuery, null)
        success.moveToFirst()
        val x = success.getInt(0)
        db.close()
        return x==1
    }



    fun getRecipeList() : ArrayList<SavedRecipe>{
        val recipeList =  ArrayList<SavedRecipe>()
        val selectQuery = "SELECT * FROM $TABLE_SAVED_RECIPES"

        val db = this.readableDatabase
        try{
            val cursor : Cursor = db.rawQuery(selectQuery,null )
            if(cursor.moveToNext()){
                do{
                    val recipe = SavedRecipe(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_RECIPE_NAME)),
                        cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_TIME)),
                        cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(KEY_RECIPE_IMAGE))

                    )
                    recipeList.add(recipe)

                }while (cursor.moveToNext())
                cursor.close()
            }

        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        Log.e("LMAO" ,"list size  ${recipeList.size}")
        return recipeList

    }
}