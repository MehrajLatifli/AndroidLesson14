package com.example.androidlesson14.api

import com.example.androidlesson14.models.Recipe
import com.example.androidlesson14.models.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiManager {

    @GET("recipes")
    fun getRecipe(): Call<RecipeResponse>

    @GET("recipes/{id}")
    fun getRecipeById(@Path("id") id: String): Call<Recipe>
}
