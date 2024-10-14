package ru.test.features.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateFormatter {
    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(dateString)

        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)

        val monthName = when (month) {
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            12 -> "декабря"
            else -> ""
        }

        return "$dayOfMonth $monthName"
    }
}
