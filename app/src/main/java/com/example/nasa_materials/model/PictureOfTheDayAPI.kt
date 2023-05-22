package com.example.nasa_materials.model

import com.example.nasa_materials.model.entities.PictureOfTheDayResponseData
import com.example.nasa_materials.utils.NASA_ENDPOINT
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface PictureOfTheDayAPI {
    @GET(NASA_ENDPOINT)
    fun getPictureOfTheDay(@Query("api_key") apiKey:String):Call<PictureOfTheDayResponseData>
}