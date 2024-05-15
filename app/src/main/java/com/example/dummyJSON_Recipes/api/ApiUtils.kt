package com.example.dummyJSON_Recipes.api

class ApiUtils {

    companion object{

        fun createApi(): IApiManager {

            return RetrofitClient.retrofit.create(IApiManager::class.java)

        }
    }

}