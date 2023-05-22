package com.example.nasa_materials.model.repository

import com.example.nasa_materials.model.PictureOfTheDayAPI
import com.example.nasa_materials.model.Repository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository {
    private val baseUrl = "https://api.nasa.gov/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }


    fun getPictureofTheDayAPI(): PictureOfTheDayAPI {
        return retrofit.create(PictureOfTheDayAPI::class.java)

    }

    override fun getPictureOfTheDay() {
        TODO("Not yet implemented")
    }
}