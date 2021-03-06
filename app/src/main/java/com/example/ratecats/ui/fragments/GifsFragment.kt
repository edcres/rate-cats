package com.example.ratecats.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ratecats.databinding.FragmentGifsBinding
import com.example.ratecats.ui.adapters.CatsListAdapter
import com.example.ratecats.ui.viewmodels.CatsViewModel

private const val TAG = "Gifs__TAG"

class GifsFragment : Fragment() {

    private var binding: FragmentGifsBinding? = null
    private val catsVm: CatsViewModel by activityViewModels()
    private lateinit var catsListAdapter: CatsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding =
            FragmentGifsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        catsListAdapter = CatsListAdapter(catsVm)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            gifsCatsRecycler.adapter = catsListAdapter
            gifsCatsRecycler.layoutManager = LinearLayoutManager(requireContext())
        }
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setObservers() {
        catsVm.allGifs.observe(viewLifecycleOwner) {
            catsListAdapter.submitList(it)
            Log.d(TAG, "gifs: ${it.size}")
        }
    }
}