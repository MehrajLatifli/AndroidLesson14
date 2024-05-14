package com.example.androidlesson14.api

class ApiUtils {

    companion object{

        fun createApi():IApiManager{

            return RetrofitClient.retrofit.create(IApiManager::class.java)

        }
    }

}