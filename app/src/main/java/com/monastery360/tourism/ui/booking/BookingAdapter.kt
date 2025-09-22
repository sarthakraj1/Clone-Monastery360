package com.monastery360.tourism.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monastery360.tourism.R
import com.monastery360.tourism.data.Booking
import com.monastery360.tourism.data.BookingStatus
import com.monastery360.tourism.databinding.ItemBookingBinding
import java.text.SimpleDateFormat
import java.util.*

class BookingAdapter(
    private val onItemClick: (Booking) -> Unit
) : ListAdapter<Booking, BookingAdapter.BookingViewHolder>(BookingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val binding = ItemBookingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookingViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookingViewHolder(
        private val binding: ItemBookingBinding,
        private val onItemClick: (Booking) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(booking: Booking) {
            binding.apply {
                // Load monastery image
                Glide.with(binding.root.context)
                    .load(booking.monasteryImageUrl)
                    .placeholder(R.drawable.placeholder_monastery)
                    .error(R.drawable.placeholder_monastery)
                    .centerCrop()
                    .into(monasteryImage)

                // Set basic info
                monasteryName.text = booking.monasteryName
                monasteryLocation.text = booking.monasteryLocation
                visitorName.text = booking.visitorName
                ticketType.text = booking.ticketType
                
                // Format and set price
                priceText.text = String.format("€%.2f", booking.totalPrice)
                
                // Format and set date
                val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                bookingDate.text = dateFormatter.format(booking.date)
                
                // Set time
                bookingTime.text = booking.time
                
                // Set group size and duration
                groupSize.text = "${booking.groupSize} ${if (booking.groupSize == 1) "person" else "people"}"
                duration.text = "${booking.duration} min"
                
                // Set booking ID
                bookingId.text = booking.id
                
                // Set status chip
                when (booking.status) {
                    BookingStatus.PENDING -> {
                        statusChip.text = "PENDING"
                        statusChip.chipBackgroundColor = ContextCompat.getColorStateList(
                            binding.root.context, R.color.warning_light
                        )
                        statusChip.setTextColor(ContextCompat.getColor(
                            binding.root.context, R.color.warning_dark
                        ))
                    }
                    BookingStatus.CONFIRMED -> {
                        statusChip.text = "CONFIRMED"
                        statusChip.chipBackgroundColor = ContextCompat.getColorStateList(
                            binding.root.context, R.color.success_light
                        )
                        statusChip.setTextColor(ContextCompat.getColor(
                            binding.root.context, R.color.success_dark
                        ))
                    }
                    BookingStatus.CANCELLED -> {
                        statusChip.text = "CANCELLED"
                        statusChip.chipBackgroundColor = ContextCompat.getColorStateList(
                            binding.root.context, R.color.error_light
                        )
                        statusChip.setTextColor(ContextCompat.getColor(
                            binding.root.context, R.color.error_dark
                        ))
                    }
                    BookingStatus.COMPLETED -> {
                        statusChip.text = "COMPLETED"
                        statusChip.chipBackgroundColor = ContextCompat.getColorStateList(
                            binding.root.context, R.color.info_light
                        )
                        statusChip.setTextColor(ContextCompat.getColor(
                            binding.root.context, R.color.info_dark
                        ))
                    }
                }

                // Set click listener
                root.setOnClickListener {
                    onItemClick(booking)
                }
            }
        }
    }

    class BookingDiffCallback : DiffUtil.ItemCallback<Booking>() {
        override fun areItemsTheSame(oldItem: Booking, newItem: Booking): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Booking, newItem: Booking): Boolean {
            return oldItem == newItem
        }
    }
}
