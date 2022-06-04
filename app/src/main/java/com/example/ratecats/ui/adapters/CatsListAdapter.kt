package com.example.ratecats.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ratecats.data.CatPhoto
import com.example.ratecats.databinding.CatPhotoItemBinding
import com.example.ratecats.ui.viewmodels.CatsViewModel

class CatsListAdapter(
    private val catsViewModel: CatsViewModel
    ) : ListAdapter<CatPhoto, CatsListAdapter.CatsViewHolder>(CatPhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        return CatsViewHolder.from(catsViewModel, parent)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CatsViewHolder private constructor(
        private val catsViewModel: CatsViewModel,
        val binding: CatPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(catPhoto: CatPhoto) {
            binding.apply {
                executePendingBindings()
            }
        }

        companion object {
            fun from(
                catsViewModel: CatsViewModel,
                parent: ViewGroup
            ): CatsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatPhotoItemBinding
                    .inflate(layoutInflater, parent, false)
                return CatsViewHolder(catsViewModel, binding)
            }
        }
    }

    class CatPhotoDiffCallback : DiffUtil.ItemCallback<CatPhoto>() {
        override fun areItemsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem == newItem
        }
    }
}