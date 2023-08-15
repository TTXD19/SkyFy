package com.example.skyfysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skyfysample.databinding.ActivityMainBinding
import com.example.skyfysample.app.weather.WeatherFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_weather, WeatherFragment.newInstance()).commit()
    }
}