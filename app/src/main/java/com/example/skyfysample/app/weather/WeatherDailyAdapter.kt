package com.example.skyfysample.app.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skyfysample.R
import com.example.skyfysample.databinding.ItemWeatherPerDailyVhBinding
import com.example.skyfysample.model.dto.DailyForecastDto
import com.example.skyfysample.widget.WeatherType
import com.example.skyfysample.widget.WeatherWidget

class WeatherDailyAdapter :
    ListAdapter<DailyForecastDto, WeatherDailyAdapter.WeatherItemViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        return WeatherItemViewHolder(
            ItemWeatherPerDailyVhBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        currentList.getOrNull(position)?.also { holder.initView(it) }
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<DailyForecastDto>() {
        override fun areItemsTheSame(oldItem: DailyForecastDto, newItem: DailyForecastDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DailyForecastDto, newItem: DailyForecastDto): Boolean {
            return oldItem == newItem
        }
    }


    inner class WeatherItemViewHolder(private val binding: ItemWeatherPerDailyVhBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initView(dailyForecastDto: DailyForecastDto) {
            binding.apply {
                tvDay.text = dailyForecastDto.date
                tvTemp.text = this.root.context.getString(
                    R.string.weather_unit,
                    dailyForecastDto.maxTemperature.toString()
                )
                imgWeatherStatus.setImageResource(
                    when (WeatherWidget.getWeatherType(dailyForecastDto.weatherCode)) {
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