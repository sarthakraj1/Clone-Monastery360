package com.monastery360.tourism.ui.booking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.monastery360.tourism.data.Booking
import com.monastery360.tourism.data.BookingRepository
import com.monastery360.tourism.data.BookingStatus
import kotlinx.coroutines.launch

class BookingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookingRepository(application)

    val allBookings: LiveData<List<Booking>> = repository.getAllBookings()

    fun getBookingsByStatus(status: BookingStatus): LiveData<List<Booking>> {
        return repository.getBookingsByStatus(status)
    }

    fun insertBooking(booking: Booking) {
        viewModelScope.launch {
            repository.addBooking(booking)
        }
    }

    fun updateBooking(booking: Booking) {
        viewModelScope.launch {
            repository.updateBooking(booking)
        }
    }

    fun updateBookingStatus(bookingId: String, status: BookingStatus) {
        viewModelScope.launch {
            repository.updateBookingStatus(bookingId, status)
        }
    }

    fun deleteBooking(booking: Booking) {
        viewModelScope.launch {
            repository.deleteBooking(booking)
        }
    }

    // Function to add sample bookings for testing
    fun addSampleBookings() {
        viewModelScope.launch {
            val sampleBookings = getSampleBookings()
            sampleBookings.forEach { booking ->
                repository.addBooking(booking)
            }
        }
    }

    private fun getSampleBookings(): List<Booking> {
        return listOf(
            Booking(
                id = "BK-2024-001",
                monasteryId = 1,
                monasteryName = "Meteora Monasteries",
                monasteryLocation = "Kalabaka, Greece",
                monasteryImageUrl = "https://images.unsplash.com/photo-1555993539-1732b0258235?w=800",
                date = java.util.Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000), // 7 days from now
                time = "10:00",
                duration = 90,
                visitorName = "John Doe",
                visitorEmail = "john.doe@email.com",
                visitorPhone = "+1234567890",
                groupSize = 2,
                specialRequests = "Wheelchair accessible tour",
                status = BookingStatus.CONFIRMED,
                totalPrice = 50.0,
                ticketType = "Standard Visit"
            ),
            Booking(
                id = "BK-2024-002",
                monasteryId = 2,
                monasteryName = "Mont-Saint-Michel",
                monasteryLocation = "Normandy, France",
                monasteryImageUrl = "https://images.unsplash.com/photo-1539650116574-75c0c6d73f6e?w=800",
                date = java.util.Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000), // 14 days from now
                time = "14:30",
                duration = 120,
                visitorName = "Jane Smith",
                visitorEmail = "jane.smith@email.com",
                visitorPhone = "+9876543210",
                groupSize = 4,
                status = BookingStatus.PENDING,
                totalPrice = 80.0,
                ticketType = "Premium Tour"
            ),
            Booking(
                id = "BK-2024-003",
                monasteryId = 3,
                monasteryName = "Rila Monastery",
                monasteryLocation = "Rila Mountains, Bulgaria",
                monasteryImageUrl = "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=800",
                date = java.util.Date(System.currentTimeMillis() + 21 * 24 * 60 * 60 * 1000), // 21 days from now
                time = "09:00",
                duration = 60,
                visitorName = "Mike Johnson",
                visitorEmail = "mike.johnson@email.com",
                visitorPhone = "+5555555555",
                groupSize = 1,
                status = BookingStatus.CONFIRMED,
                totalPrice = 25.0,
                ticketType = "Standard Visit"
            )
        )
    }
}