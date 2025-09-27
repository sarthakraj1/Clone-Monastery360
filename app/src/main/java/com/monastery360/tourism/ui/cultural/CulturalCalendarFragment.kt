package com.monastery360.tourism.ui.cultural

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.monastery360.tourism.data.CulturalEventRepository
import com.monastery360.tourism.databinding.FragmentCulturalCalendarBinding

class CulturalCalendarFragment : Fragment() {

    private var _binding: FragmentCulturalCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CulturalEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCulturalCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CulturalEventAdapter()
        binding.eventsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = this@CulturalCalendarFragment.adapter
        }

        val events = CulturalEventRepository.getAssamCulturalEvents()
        adapter.submitList(events)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

