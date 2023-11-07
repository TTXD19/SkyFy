package com.example.skyfysample.app.location_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skyfysample.data.repository.DataResult
import com.example.skyfysample.data.repository.weather.WeatherRepository
import com.example.skyfysample.model.dto.HourlyWeatherDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationDetailViewModel: ViewModel() {

    private val weatherRepository = WeatherRepository()

    val hourlyWeatherLiveData: MutableLiveData<HourlyWeatherDto?> = MutableLiveData()


    fun getHourlyWeatherInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDto = weatherRepository.getHourlyWeatherInfo(25.0478, 121.5319, "1")
            if (weatherDto is DataResult.Success) {
                hourlyWeatherLiveData.postValue(weatherDto.body)
            }
        }
    }

}