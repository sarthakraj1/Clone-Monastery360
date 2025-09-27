package com.monastery360.tourism.data

data class CulturalEvent(
    val id: Int,
    val title: String,
    val description: String,
    val month: Int, // 1-12
    val day: Int, // 1-31
    val location: String,
    val imageUrl: String,
    val year: Int? = null
) {
    fun formattedDate(): String {
        val months = listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )
        val monthName = if (month in 1..12) months[month - 1] else ""
        return if (year != null) "$monthName $day, $year" else "$monthName $day"
    }
}

