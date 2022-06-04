package com.example.ratecats.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.ratecats.R
import com.example.ratecats.databinding.FragmentCategoriesBinding
import com.example.ratecats.ui.viewmodels.CatsViewModel

class CategoriesFragment : Fragment() {

    private var binding: FragmentCategoriesBinding? = null
    private val catsViewModel: CatsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding =
            FragmentCategoriesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}