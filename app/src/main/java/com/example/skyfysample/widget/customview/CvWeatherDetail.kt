package com.example.skyfysample.widget.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.skyfysample.databinding.CvWeatherDetailBinding

class CvWeatherDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: CvWeatherDetailBinding

    init {
        binding = CvWeatherDetailBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setWeatherImage(resId: Int) {
        binding.imgWeather.setImageResource(resId)
    }

    fun setWeatherValue(value: String) {
        binding.tvWeatherValue.text = value
    }

    fun setWeatherDescription(description: String) {
        binding.tvWeatherDesc.text = description
    }
}