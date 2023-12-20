package com.example.nasa_materials.repository

import com.example.nasa_materials.repository.network.entities.PictureOfDayDTO

sealed class AppState {
    data class Success(val serverResponseData: PictureOfDayDTO) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
