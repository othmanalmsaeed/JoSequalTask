package com.othman.josequaltask.remote

import com.othman.josequaltask.model.PlaceItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {


    @GET("maps.php")
    fun getPlaces(): Call<List<PlaceItem>>
}