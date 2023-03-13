package com.othman.josequaltask.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.othman.josequaltask.model.PlaceItem
import com.othman.josequaltask.remote.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceViewModel: ViewModel() {

    //LiveData
    var placeItemList = MutableLiveData<List<PlaceItem>>()

    fun getPlaceData(){
        val apiService = Api.getInstance()
        apiService.getPlaces().enqueue(object : Callback<List<PlaceItem>>{
            override fun onResponse(
                call: Call<List<PlaceItem>>,
                response: Response<List<PlaceItem>>
            ) {
                placeItemList.value = response.body()
            }

            override fun onFailure(call: Call<List<PlaceItem>>, t: Throwable) {
                Log.e("RequestError: ", t.message.toString())
            }

        })
    }

}