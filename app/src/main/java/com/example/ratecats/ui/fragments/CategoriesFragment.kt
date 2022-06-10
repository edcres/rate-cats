package com.example.ratecats.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ratecats.databinding.FragmentCategoriesBinding
import com.example.ratecats.ui.adapters.CatsListAdapter
import com.example.ratecats.ui.viewmodels.CatsViewModel
import kotlin.math.log

private const val TAG = "Categories__TAG"

class CategoriesFragment : Fragment() {

    private var binding: FragmentCategoriesBinding? = null
    private val catsVm: CatsViewModel by activityViewModels()
    private lateinit var catsListAdapter: CatsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding =
            FragmentCategoriesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catsVm.setupLocalBackend(requireActivity().application)
        catsListAdapter = CatsListAdapter(catsVm)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            categoriesCatsRecycler.adapter = catsListAdapter
            categoriesCatsRecycler.layoutManager = LinearLayoutManager(requireContext())
        }
        setObservers()
        Log.d(TAG, "onViewCreated: called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setObservers() {
        catsVm.photos.observe(viewLifecycleOwner) {
            catsListAdapter.submitList(it)
            Log.d(TAG, "Categories: ${it.size}")
        }
    }
}