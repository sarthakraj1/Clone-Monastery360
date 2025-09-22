package com.monastery360.tourism.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings ORDER BY bookingDate DESC")
    fun getAllBookings(): LiveData<List<Booking>>

    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    suspend fun getBookingById(bookingId: String): Booking?

    @Query("SELECT * FROM bookings WHERE status = :status ORDER BY date ASC")
    fun getBookingsByStatus(status: BookingStatus): LiveData<List<Booking>>

    @Query("SELECT * FROM bookings WHERE monasteryId = :monasteryId ORDER BY date ASC")
    fun getBookingsByMonastery(monasteryId: Int): LiveData<List<Booking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: Booking)

    @Update
    suspend fun updateBooking(booking: Booking)

    @Delete
    suspend fun deleteBooking(booking: Booking)

    @Query("UPDATE bookings SET status = :newStatus WHERE id = :bookingId")
    suspend fun updateBookingStatus(bookingId: String, newStatus: BookingStatus)

    @Query("DELETE FROM bookings WHERE id = :bookingId")
    suspend fun deleteBookingById(bookingId: String)

    @Query("SELECT COUNT(*) FROM bookings")
    suspend fun getBookingCount(): Int
}