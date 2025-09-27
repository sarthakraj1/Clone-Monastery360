package com.monastery360.tourism.ui.monasteries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.monastery360.tourism.R
import com.monastery360.tourism.data.MonasteryRepository
import com.monastery360.tourism.databinding.FragmentMonasteriesBinding

class MonasteriesFragment : Fragment() {

    private var _binding: FragmentMonasteriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var monasteryAdapter: MonasteryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonasteriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        loadMonasteries()
    }

    private fun setupRecyclerView() {
        monasteryAdapter = MonasteryAdapter { monastery ->
            val intent = Intent(requireContext(), MonasteryGalleryActivity::class.java)
            intent.putExtra(MonasteryGalleryActivity.EXTRA_MONASTERY_ID, monastery.id)
            intent.putExtra(MonasteryGalleryActivity.EXTRA_MONASTERY_NAME, monastery.name)
            startActivity(intent)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = monasteryAdapter
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun loadMonasteries() {
        val monasteries = MonasteryRepository.getMonasteries()
        monasteryAdapter.submitList(monasteries)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
