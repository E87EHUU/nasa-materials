package com.example.nasa_materials.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasa_materials.BuildConfig
import com.example.nasa_materials.model.PictureOfTheDayResponseData
import com.example.nasa_materials.model.RepositoryImpl
import com.example.nasa_materials.view.PictureOfTheDayFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
                               private val repositoryImpl:RepositoryImpl= RepositoryImpl()):
    ViewModel() {

    fun getLiveData():MutableLiveData<AppState> {
        return liveData
    }

    fun sendRequest(){
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureofTheDayAPI().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }

    private val callback = object : Callback<PictureOfTheDayResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if(response.isSuccessful) {
                liveData.postValue(AppState.Success(response.body()!!))
            } else {
                liveData.postValue(AppState.Error(throw IllegalAccessException("Что-то пошло не так")))
            }
            response.body()
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }
    }


