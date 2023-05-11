package com.example.nasa_materials.model

import com.example.nasa_materials.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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