package com.example.ratecats.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ratecats.databinding.FragmentFavoritesBinding
import com.example.ratecats.ui.adapters.CatsListAdapter
import com.example.ratecats.ui.viewmodels.CatsViewModel

private const val TAG = "Favorites__TAG"

class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null
    private val catsVm: CatsViewModel by activityViewModels()
    private lateinit var catsListAdapter: CatsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding =
            FragmentFavoritesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        catsListAdapter = CatsListAdapter(catsVm)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            favoritesCatsRecycler.adapter = catsListAdapter
            favoritesCatsRecycler.layoutManager = LinearLayoutManager(requireContext())
        }
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setObservers() {
        catsVm.savedFavourites.observe(viewLifecycleOwner) {
            catsListAdapter.submitList(it)
            Log.d(TAG, "favorites: ${it.size}")
        }
    }
}