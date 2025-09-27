package com.monastery360.tourism.ui.monasteries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.monastery360.tourism.databinding.ActivityMonasteryGalleryBinding

class MonasteryGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMonasteryGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonasteryGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val monasteryId = intent.getIntExtra(EXTRA_MONASTERY_ID, -1)
        val monasteryName = intent.getStringExtra(EXTRA_MONASTERY_NAME) ?: "Monastery"

        supportActionBar?.title = "$monasteryName Archive"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val images = MonasteryGalleryRepository.getArchiveImages(monasteryId)

        val adapter = PhotoGridAdapter()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MonasteryGalleryActivity, 2)
            this.adapter = adapter
        }
        adapter.submitList(images)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_MONASTERY_ID = "extra_monastery_id"
        const val EXTRA_MONASTERY_NAME = "extra_monastery_name"
    }
}

