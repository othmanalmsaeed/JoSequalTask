package com.othman.josequaltask.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {


    companion object{

        private const val BASE_URL = "https://josequal.net"
        private var instance : ApiService? = null

        @Synchronized
        fun getInstance(): ApiService {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiService::class.java)
            return instance as ApiService
        }

    }
}