package com.monastery360.tourism.ui.cultural

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monastery360.tourism.data.CulturalEvent
import com.monastery360.tourism.databinding.ItemCulturalEventBinding

class CulturalEventAdapter : ListAdapter<CulturalEvent, CulturalEventAdapter.EventViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemCulturalEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EventViewHolder(private val binding: ItemCulturalEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CulturalEvent) {
            binding.eventTitle.text = item.title
            binding.eventLocation.text = item.location
            binding.eventDate.text = item.formattedDate()

            Glide.with(binding.eventImage.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(binding.eventImage)
        }
    }

    class Diff : DiffUtil.ItemCallback<CulturalEvent>() {
        override fun areItemsTheSame(oldItem: CulturalEvent, newItem: CulturalEvent): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CulturalEvent, newItem: CulturalEvent): Boolean = oldItem == newItem
    }
}

