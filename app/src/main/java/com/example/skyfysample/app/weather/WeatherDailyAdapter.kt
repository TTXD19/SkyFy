package com.example.skyfysample.app.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skyfysample.R
import com.example.skyfysample.databinding.ItemWeatherPerDailyVhBinding
import com.example.skyfysample.model.weather.DailyForecast
import com.example.skyfysample.widget.TimeCustomWidget
import com.example.skyfysample.widget.WeatherType
import com.example.skyfysample.widget.WeatherWidget

class WeatherDailyAdapter :
    ListAdapter<DailyForecast, WeatherDailyAdapter.WeatherItemViewHolder>(ItemDiffUtil()) {

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

    class ItemDiffUtil : DiffUtil.ItemCallback<DailyForecast>() {
        override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
            return oldItem == newItem
        }
    }


    inner class WeatherItemViewHolder(private val binding: ItemWeatherPerDailyVhBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initView(dailyForecast: DailyForecast) {
            binding.apply {
                tvDay.text = dailyForecast.date
                tvTemp.text = this.root.context.getString(
                    R.string.weather_unit,
                    dailyForecast.maxTemperature.toString()
                )
                imgWeatherStatus.setImageResource(
                    when (WeatherWidget.getWeatherType(dailyForecast.weatherCode)) {
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