package com.monastery360.tourism.data

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class BookingRepository(context: Context) {
    private val bookingDao = BookingDatabase.getDatabase(context).bookingDao()
    
    fun getAllBookings(): LiveData<List<Booking>> = bookingDao.getAllBookings()
    
    fun getBookingsByStatus(status: BookingStatus): LiveData<List<Booking>> = 
        bookingDao.getBookingsByStatus(status)
    
    fun getBookingsByMonastery(monasteryId: Int): LiveData<List<Booking>> = 
        bookingDao.getBookingsByMonastery(monasteryId)
    
    suspend fun addBooking(booking: Booking) = withContext(Dispatchers.IO) {
        bookingDao.insertBooking(booking)
    }
    
    suspend fun getBookingById(id: String): Booking? = withContext(Dispatchers.IO) {
        bookingDao.getBookingById(id)
    }
    
    suspend fun updateBooking(booking: Booking) = withContext(Dispatchers.IO) {
        bookingDao.updateBooking(booking)
    }
    
    suspend fun updateBookingStatus(id: String, status: BookingStatus) = withContext(Dispatchers.IO) {
        bookingDao.updateBookingStatus(id, status)
    }
    
    suspend fun deleteBooking(booking: Booking) = withContext(Dispatchers.IO) {
        bookingDao.deleteBooking(booking)
    }
    
    suspend fun deleteBookingById(id: String) = withContext(Dispatchers.IO) {
        bookingDao.deleteBookingById(id)
    }
    
    fun getTimeSlots(date: Date): List<TimeSlot> {
        return listOf(
            TimeSlot("09:00", true),
            TimeSlot("10:00", true),
            TimeSlot("11:00", true),
            TimeSlot("12:00", false),
            TimeSlot("13:00", true),
            TimeSlot("14:00", true),
            TimeSlot("15:00", true),
            TimeSlot("16:00", true),
            TimeSlot("17:00", false)
        )
    }
    
    fun getAvailableDates(): List<Date> {
        val calendar = Calendar.getInstance()
        val dates = mutableListOf<Date>()
        
        // Add next 30 days
        for (i in 1..30) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            dates.add(calendar.time)
        }
        
        return dates
    }
}
