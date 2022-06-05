package com.example.ratecats.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.ratecats.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartFragment : Fragment() {

    private lateinit var bottomNavBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bottom navigation
        bottomNavBar = requireActivity().findViewById(R.id.bottom_nav)
        requireParentFragment().requireView()
        val navController = Navigation.findNavController(requireParentFragment().requireView())
        bottomNavBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.gifs_btn -> {
                    navController.navigate(R.id.action_categoriesFragment_to_gifsFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.categories_btn -> {
                    navController.navigate(R.id.action_favoritesFragment_to_categoriesFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.favorites_btn -> {
                    navController.navigate(R.id.action_categoriesFragment_to_favoritesFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}