package com.example.skyfysample.app.location_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skyfysample.data.repository.DataResult
import com.example.skyfysample.data.repository.weather.AqiRepository
import com.example.skyfysample.data.repository.weather.WeatherRepository
import com.example.skyfysample.model.dto.DailyAqiWeatherDto
import com.example.skyfysample.model.dto.HourlyWeatherDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationDetailViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository()
    private val aqiRepository = AqiRepository()

    val hourlyWeatherLiveData: MutableLiveData<HourlyWeatherDto?> = MutableLiveData()
    val aqiWeatherLiveData: MutableLiveData<DailyAqiWeatherDto> = MutableLiveData()


    fun getHourlyWeatherInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDto = weatherRepository.getHourlyWeatherInfo(25.0478, 121.5319, "1")
            if (weatherDto is DataResult.Success) {
                hourlyWeatherLiveData.postValue(weatherDto.body)
            }
        }
    }

    fun getDailyAqiInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val aqiDTO = aqiRepository.getDailyAqiInfo(25.0478, 121.5319)
            if (aqiDTO is DataResult.Success) {
                aqiWeatherLiveData.postValue(aqiDTO.body)
            }
        }
    }

}