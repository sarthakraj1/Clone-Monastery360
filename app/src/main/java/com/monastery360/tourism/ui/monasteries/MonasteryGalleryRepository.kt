package com.monastery360.tourism.ui.monasteries

object MonasteryGalleryRepository {
    fun getArchiveImages(monasteryId: Int): List<String> {
        // Sample placeholder images; replace with real archive later
        return listOf(
            "https://images.unsplash.com/photo-1549887534-3db1bd59dcca?w=800",
            "https://images.unsplash.com/photo-1544717305-996b815c338c?w=800",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470?w=800",
            "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=800",
            "https://images.unsplash.com/photo-1491553895911-0055eca6402d?w=800"
        )
    }
}

