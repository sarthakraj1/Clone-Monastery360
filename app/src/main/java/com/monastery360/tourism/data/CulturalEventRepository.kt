package com.monastery360.tourism.data

object CulturalEventRepository {
    fun getAssamCulturalEvents(): List<CulturalEvent> {
        return listOf(
            CulturalEvent(
                id = 1,
                title = "Bihu (Rongali Bihu)",
                description = "Assam's vibrant New Year festival with dance, music, and feasts.",
                month = 4,
                day = 14,
                location = "Across Assam",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Bihu_dance.jpg/640px-Bihu_dance.jpg"
            ),
            CulturalEvent(
                id = 2,
                title = "Ambubachi Mela",
                description = "Annual festival at Kamakhya Temple, celebrating fertility and the goddess.",
                month = 6,
                day = 22,
                location = "Kamakhya Temple, Guwahati",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Kamakhya_temple.jpg/640px-Kamakhya_temple.jpg"
            ),
            CulturalEvent(
                id = 3,
                title = "Majuli Raas",
                description = "Traditional Raas Leela performances on the river island of Majuli.",
                month = 11,
                day = 10,
                location = "Majuli, Assam",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Traditional_Raas_Leela.jpg/640px-Traditional_Raas_Leela.jpg"
            ),
            CulturalEvent(
                id = 4,
                title = "Bohag Bihu Cultural Nights",
                description = "Evening cultural programs featuring Bihu dance and folk music.",
                month = 4,
                day = 15,
                location = "Guwahati, Assam",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Bihu_Dancers.jpg/640px-Bihu_Dancers.jpg"
            )
        )
    }
}

