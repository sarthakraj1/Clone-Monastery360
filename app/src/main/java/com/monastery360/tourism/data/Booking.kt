package com.monastery360.tourism.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "bookings")
@TypeConverters(Converters::class)
data class Booking(
    @PrimaryKey
    val id: String,
    val monasteryId: Int,
    val monasteryName: String,
    val monasteryLocation: String,
    val monasteryImageUrl: String,
    val date: Date,
    val time: String,
    val duration: Int, // in minutes
    val visitorName: String,
    val visitorEmail: String,
    val visitorPhone: String,
    val groupSize: Int,
    val specialRequests: String? = null,
    val status: BookingStatus = BookingStatus.PENDING,
    val bookingDate: Date = Date(), // When the booking was made
    val totalPrice: Double = 0.0,
    val ticketType: String = "Standard Visit"
)

enum class BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}

data class TimeSlot(
    val time: String,
    val available: Boolean,
    val maxCapacity: Int = 20
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromBookingStatus(status: BookingStatus): String {
        return status.name
    }

    @TypeConverter
    fun toBookingStatus(status: String): BookingStatus {
        return BookingStatus.valueOf(status)
    }
}
