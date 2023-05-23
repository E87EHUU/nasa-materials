package com.example.nasa_materials.model

import com.example.nasa_materials.model.entities.PictureOfDayDTO
import com.example.nasa_materials.utils.API_KEY
import com.example.nasa_materials.utils.NASA_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaAPI {
    @GET(NASA_ENDPOINT)
    fun getPictureOfTheDay(@Query(API_KEY) apiKey:String):Call<PictureOfDayDTO>
}