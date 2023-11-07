package com.example.skyfysample.app.location_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.skyfysample.databinding.FragmentLocationDetailBinding

class LocationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding

    private val viewModel: LocationDetailViewModel by viewModels()

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

        viewModel.getHourlyWeatherInfo()
    }
}