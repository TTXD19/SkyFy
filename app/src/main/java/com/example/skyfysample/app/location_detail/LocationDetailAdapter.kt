package com.example.skyfysample.app.location_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skyfysample.R
import com.example.skyfysample.databinding.ItemWeatherPerHourVhBinding
import com.example.skyfysample.model.dto.HourlyForecastDto
import com.example.skyfysample.widget.TimeCustomWidget
import com.example.skyfysample.widget.WeatherType
import com.example.skyfysample.widget.WeatherWidget

class LocationDetailAdapter :
    ListAdapter<HourlyForecastDto, LocationDetailAdapter.WeatherItemViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        return WeatherItemViewHolder(
            ItemWeatherPerHourVhBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        currentList.getOrNull(position)?.also { holder.initView(it) }
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<HourlyForecastDto>() {
        override fun areItemsTheSame(
            oldItem: HourlyForecastDto,
            newItem: HourlyForecastDto
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HourlyForecastDto,
            newItem: HourlyForecastDto
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class WeatherItemViewHolder(private val binding: ItemWeatherPerHourVhBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initView(hourlyForecastDto: HourlyForecastDto) {
            binding.apply {
                tvTime.text = TimeCustomWidget.convertToLocalTime(hourlyForecastDto.time)
                tvTemp.text = this.root.context.getString(
                    R.string.weather_unit,
                    hourlyForecastDto.temperature.toString()
                )
                imgWeatherStatus.setImageResource(
                    when (WeatherWidget.getWeatherType(hourlyForecastDto.weatherCode)) {
                        WeatherType.SUNNY -> R.drawable.sunny
                        WeatherType.CLOUDY -> R.drawable.cloudy
                        WeatherType.FREEZING -> R.drawable.freezing
                        WeatherType.RAINY -> R.drawable.rainy
                        WeatherType.THUNDER -> R.drawable.thunder
                    }
                )
            }
        }
    }
}