package com.example.skyfysample.app.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skyfysample.data.repository.DataResult
import com.example.skyfysample.data.repository.weather.WeatherRepository
import com.example.skyfysample.model.weather.WeatherDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository()

    val weatherLiveData: MutableLiveData<WeatherDto?> = MutableLiveData()


    fun getWeatherInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDto = weatherRepository.getWeatherInfo(25.0478, 121.5319)
            if (weatherDto is DataResult.Success) {
                weatherLiveData.postValue(weatherDto.body)
            }
        }
    }

}