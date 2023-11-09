package com.example.skyfysample.app.location_detail

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skyfysample.R
import com.example.skyfysample.databinding.FragmentLocationDetailBinding
import com.example.skyfysample.model.dto.DailyAqiWeatherDto

class LocationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding

    private val viewModel: LocationDetailViewModel by viewModels()
    private val locationDetailAdapter: LocationDetailAdapter by lazy {
        LocationDetailAdapter()
    }

    companion object {
        fun newInstance(): LocationDetailFragment {
            return LocationDetailFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
    }

    private fun initView() {
        binding.rvHourlyForecast.apply {
            adapter = locationDetailAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
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
    }

    private fun initData() {
        viewModel.apply {
            getHourlyWeatherInfo()
            hourlyWeatherLiveData.observe(viewLifecycleOwner) {
                locationDetailAdapter.submitList(it?.hourlyForecasts)
            }

            getDailyAqiInfo()
            aqiWeatherLiveData.observe(viewLifecycleOwner) {
                initAqiInfo(it)
            }
        }
    }

    private fun initAqiInfo(dailyAqiDTO: DailyAqiWeatherDto) {
        binding.apply {
            tvAqiValue.text = dailyAqiDTO.current.europeanAqi.toString()
            cwUvIndex.setWeatherValue(dailyAqiDTO.current.uvIndex.toString())
        }
    }
}