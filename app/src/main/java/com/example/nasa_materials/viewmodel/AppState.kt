package com.example.nasa_materials.viewmodel

import com.example.nasa_materials.model.entities.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData):AppState()
    data class Error(val error: Throwable):AppState()
    object Loading:AppState()
}
