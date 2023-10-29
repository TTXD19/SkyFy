package com.example.skyfysample.app.weather

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.skyfysample.R
import com.example.skyfysample.databinding.FragmentWeatherBinding
import com.example.skyfysample.model.weather.DailyForecast
import com.example.skyfysample.widget.WeatherType
import com.example.skyfysample.widget.WeatherWidget

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding

    private val viewModel: WeatherViewModel by viewModels()

    private val weatherAdapter: WeatherAdapter by lazy {
        WeatherAdapter()
    }

    private val weatherDailyAdapter: WeatherDailyAdapter by lazy {
        WeatherDailyAdapter()
    }

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getWeatherInfo()
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            weatherAdapter.submitList(it?.hourlyForecasts)
            weatherDailyAdapter.submitList(it?.dailyForecasts)
            initGeneralView(it?.dailyForecasts?.first())
        }
    }

    private fun initView() {
        binding.rvHourlyForecast.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val position = parent.getChildAdapterPosition(view)
                    if (position != 0) {
                        val leftSpacing =
                            parent.context.resources.getDimensionPixelSize(R.dimen.ten_dp_spacing)
                        outRect.left = leftSpacing
                        outRect.right = 0
                        outRect.bottom = 0
                        outRect.top = 0
                    }
                }
            })
        }

        binding.rvDailyForecast.apply {
            adapter = weatherDailyAdapter
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val position = parent.getChildAdapterPosition(view)
                    if (position != 0) {
                        val topSpacing =
                            parent.context.resources.getDimensionPixelSize(R.dimen.ten_dp_spacing)
                        outRect.left = 0
                        outRect.right = 0
                        outRect.bottom = 0
                        outRect.top = topSpacing
                    }
                }
            })
        }
    }

    private fun initGeneralView(dailyForecast: DailyForecast?) {
        dailyForecast?.also {
            binding.apply {
                tvDate.text = dailyForecast.time
                tvTime.text = "3.30 PM"
                tvLocation.text = "台北市"
                tvUpdatedTime.text = "上次更新時間：3.00 PM"
                tvDegree.text = getString(R.string.weather_unit, dailyForecast.maxTemperature.toString())
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