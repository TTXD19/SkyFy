package com.example.skyfysample.app.location_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skyfysample.R
import com.example.skyfysample.app.weather.WeatherFragment
import com.example.skyfysample.databinding.ActivityLocationDetailBinding

class LocationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationDetailBinding

    companion object {
        fun newInstance(activity: Activity) {
            val intent = Intent(activity, LocationDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_weather, LocationDetailFragment.newInstance()).commit()
    }
}