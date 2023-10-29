package com.example.skyfysample.widget

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object TimeCustomWidget {

    fun convertToLocalTime(timestamp: String, timeZone: String = "Asia/Taipei"): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm a", Locale.TAIWAN)

        // Parse as LocalDateTime and specify it's in UTC
        val utcDateTime = LocalDateTime.parse(timestamp, inputFormatter).atZone(ZoneId.of("UTC"))

        // Convert to desired timezone
        val localDateTime = utcDateTime.withZoneSameInstant(ZoneId.of(timeZone))

        return outputFormatter.format(localDateTime)
    }
}